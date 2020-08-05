package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.*;
import dushkof.seaWars.repo.FieldRepo;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    FileInputStream fis;
    Properties property = new Properties();

    @Resource
    FieldRepo fieldRepo;

    @Resource
    GameRepo gameRepo;

    @Resource
    UserRepo userRepo;

    @Override
    public String createGame(String name) {
        User user = userRepo.findByName(name);
        if ( user == null ) {
            LOGGER.info("User " + name + " not created");
            return "NOK";
        }
        try {
            List<Game> games = gameRepo.findByUserHost(user);
            if ( !games.isEmpty() ) {
                List<Game> notFinishedGames = new ArrayList<>();
                for (int i = 0; i < games.size(); i++) {
                    Game game = games.get(i);
                    if ( !game.getFinished() ) {
                        if ( !game.getStarted() ) {
                            notFinishedGames.add(game);
                        } else {
                            LOGGER.info("Game already started for " + name);
                            return "NOK";
                        }
                    }
                }
                LOGGER.info("Delete not started & not finished game for " + user.getName());
                gameRepo.deleteAll(notFinishedGames);
            }
            LOGGER.info("Create game for " + user.getName());
            return createGame(user);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return "NOK";
        }
    }

    private String createGame(User user) {
        LOGGER.info("Game for user " + user.getName() + " is creating");
        gameRepo.save(new Game(user));
        return "OK";
    }

    @Override
    public String connectSecondUser(Long id, String name) {
        try {
            User secondUser = userRepo.findByName(name);
            Game game = gameRepo.findGameById(id);
            if (secondUser.equals(game.getUserHost())){
                LOGGER.info("User try to connect to the same game");
                return "NOK";
            }
            game.setSecondUser(secondUser);
            gameRepo.save(game);
            LOGGER.info("User " + name + " is connecting for " + game.getUserHost().getName());
            return "OK";
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return "NOK";
        }
    }

    @Override
    public List<Game> foundNewGames() {
        List<Game> games = gameRepo.findGameBySecondUser(null);
        LOGGER.info("Send free games");
        return checkRepeatGames(games);
    }

    @Override
    public Game getGameById(Long gameId) {
        Game game = gameRepo.findGameById(gameId);
        return game;
    }

    public List<Game> checkRepeatGames(List<Game> games) {
        List<Game> newGames = new ArrayList<>();
        for (Game game : games) {
            if ( countUserHosts(games, game.getUserHost()) == 1 ) {
                newGames.add(game);
            } else gameRepo.delete(game);
        }
        return newGames;
    }

    public int countUserHosts(List<Game> games, User userHost) {
        int count = 0;
        for (Game game : games) {
            if ( game.getUserHost().equals(userHost) ) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Field createField(String name, Long gameId) throws IOException {
        Game game = gameRepo.findGameById(gameId);
        User user = userRepo.findByName(name);
        Field field = createField();
        field.setGame(game);
        field.setUser(user);
        try {
            fieldRepo.save(field);
            LOGGER.info("Field created for " + name);
            return field;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    private Field createField() throws IOException {
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
            Integer FIELD_SIZE = Integer.valueOf(property.getProperty("db.field_size"));
            Field field = new Field();
            List<Cell> cells = new ArrayList<>();
            for (int i = 1; i <= FIELD_SIZE; i++) {
                for (int q = 1; q <= FIELD_SIZE; q++) {
                    cells.add(new Cell(i, q));
                }
            }
            List<Ship> ships = new ArrayList<>();
            Ship ship1 = new Ship(2);
            Ship ship2 = new Ship(2);
            ships.add(ship1);
            ships.add(ship2);
            field.setShips(ships);
            field.setCells(cells);
            return field;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public String leaveGame(String name, Long gameId) {
        try {
            Game game = gameRepo.findGameById(gameId);
            if (BooleanUtils.isNotTrue(game.getStarted())) {
                if (game.getUserHost().getName().equals(name)) {
                    LOGGER.info("User " + name + " leave from the game");
                    LOGGER.info("Game " + game.getId() + " is deleted");
                    game.setFinished(true);
                } else if (game.getSecondUser().getName().equals(name)) {
                    LOGGER.info("User " + name + " leave from the game");
                    LOGGER.info("Game " + game.getId() + " is free to connect new second User");
                    game.setSecondUser(null);
                }
            } else {
                if (game.getUserHost().getName().equals(name) || game.getSecondUser().getName().equals(name)) {
                    LOGGER.info("User " + name + " leave from the game");
                    LOGGER.info("Game " + game.getId() + " is deleted");
                    game.setFinished(true);
                }
            }
            gameRepo.save(game);
            return "OK";
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return "NOK";
        }
    }

    @Override
    public void startGame(Long gameId) {
        Game game = gameRepo.getOne(gameId);
        if (Optional.ofNullable(game.getJoinField()).isPresent() && Optional.ofNullable(game.getHostField()).isPresent()) {
            if (game.getJoinField().isReady() && game.getHostField().isReady()) {
                game.setStarted(Boolean.TRUE);
                gameRepo.save(game);
            }
        }
    }

}