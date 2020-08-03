package dushkof.seaWars.services.impl;

import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl implements GameService {

    @Override
    public void startGame() {
        System.out.println("Game is started!");
    }

    @Resource
    GameRepo gameRepo;

    @Resource
    UserRepo userRepo;

    @Override
    public String createGame(String name) {
        User user = userRepo.findByName(name);
        if (user == null) {
            return "NOK";
        }
        try {
            List<Game> games = gameRepo.findByUserHost(user);
            if (!games.isEmpty()) {
                List<Game> notFinishedGames = new ArrayList<>();
                for (int i = 0; i < games.size(); i++) {
                    Game game = games.get(i);
                    if (!game.getFinished()) {
                        if (!game.getStarted()) {
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
        try{
            User secondUser = userRepo.findByName(name);
            Game game = gameRepo.findGameById(id);
            game.setSecondUser(secondUser);
            gameRepo.save(game);
            return "OK";}
        catch (Exception e){
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
            if (countUserHosts(games, game.getUserHost()) == 1) {
                newGames.add(game);
            } else gameRepo.delete(game);
        }
        return newGames;
    }

    public int countUserHosts(List<Game> games, User userHost) {
        int count = 0;
        for (Game game : games) {
            if (game.getUserHost().equals(userHost)) {
                count++;
            }
        }
        return count;
    }

    public void setGameRepo(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }
}
