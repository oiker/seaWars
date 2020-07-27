package dushkof.seaWars.services.impl;

import dushkof.seaWars.dao.GameDao;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.services.GameService;

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

        gameDao.hostJoin(name);
        return "Game creating";
    }

    @Override
    public String connectSecondUser(Integer id, String name) {
        gameDao.playerJoin(id, name);
        return "Have fun";
    }
    @Override
    public List<Game> foundNewGames() {
        return getGameDao().foundFreeGames();
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public GameDao getGameDao() {
        return gameDao;
    }
}
