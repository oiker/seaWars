package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.*;
import dushkof.seaWars.repo.FieldRepo;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private Integer FIELD_SIZE = 4;

    @Override
    public void startGame() {
        System.out.println("Game is started!");
    }

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
                            return "NOK";
                        }
                    }
                }
                gameRepo.deleteAll(notFinishedGames);
            }
            return createGame(user);
        } catch (Exception e) {
            e.getMessage();
            return "NOK";
        }
    }

    private String createGame(User user) {
        gameRepo.save(new Game(user));
        return "OK";
    }

    @Override
    public String connectSecondUser(Long id, String name) {
        try {
            User secondUser = userRepo.findByName(name);
            Game game = gameRepo.findGameById(id);
            game.setSecondUser(secondUser);
            gameRepo.save(game);
            return "OK";
        } catch (Exception e) {
            System.out.println(e);
            return "NOK";
        }
    }

    @Override
    public List<Game> foundNewGames() {
        List<Game> games = gameRepo.findGameBySecondUser(null);
        return checkRepeatGames(games);
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
    public Field createField(String name, Long gameId) {
        Game game = gameRepo.findGameById(gameId);
        User user = userRepo.findByName(name);
        Field field = createField();
        field.setGame(game);
        field.setUser(user);
        try {
            fieldRepo.save(field);
            return field;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    private Field createField() {
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
    }
}
