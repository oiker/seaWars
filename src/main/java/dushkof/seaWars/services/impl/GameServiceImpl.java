package dushkof.seaWars.services.impl;

import dushkof.seaWars.dao.GameDao;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.services.GameService;

import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl implements GameService {
    private GameDao gameDao;

    @Override
    public String init() {
        return gameDao.init();
    }

    @Override
    public void startGame() {
        System.out.println("Game is started!");
    }

    @Override
    public String createGame(String name) {
        Boolean checkIfGameIsNotFinished = getGameDao().checkIfGameIsNotFinished(name);
        if(checkIfGameIsNotFinished) {
            return "NOK";
        }
        return gameDao.hostJoin(name);
    }

    @Override
    public String connectSecondUser(Integer id, String name) {
        gameDao.playerJoin(id, name);
        return "Have fun";
    }
    @Override
    public List<Game> foundNewGames() {
        List<Game> games = getGameDao().foundFreeGames();
        return checkRepeatGames(games);
    }

    public List<Game> checkRepeatGames(List<Game> games) {
        List<Game> newGames = new ArrayList<>();
        for (Game game : games) {
            if (countUserHosts(games, game.getUserHost()) == 1) {
                newGames.add(game);
            }
        }
        return newGames;
    }

    public int countUserHosts(List<Game> games, String userHost) {
        int count = 0;
        for (Game game : games) {
            if (game.getUserHost().equals(userHost)) {
                count++;
            }
        }
        return count;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public GameDao getGameDao() {
        return gameDao;
    }
}
