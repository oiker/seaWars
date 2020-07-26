package dushkof.seaWars.services.impl;

import dushkof.seaWars.dao.GameDao;
import dushkof.seaWars.services.GameService;

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
    public String connectSecondUser(int id, String name) {
        gameDao.playerJoin(id, name);
        return "Have fun";
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public GameDao getGameDao() {
        return gameDao;
    }
}
