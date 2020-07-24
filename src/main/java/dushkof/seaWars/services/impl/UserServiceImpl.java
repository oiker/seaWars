package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.UserController;
import dushkof.seaWars.dao.UserDao;
import dushkof.seaWars.dao.impl.UserDaoImpl;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.springframework.beans.factory.annotation.Required;

public class UserServiceImpl implements UserService {
    private GameService gameService;
    private UserDao userDao;

    @Override
    public String sayHi() {
        gameService.startGame();
        return "hi";
    }

    @Override
    public String userData(String name, String password) {
        return userDao.createUser(name, password);
    }
    public GameService getGameService() {
        return gameService;
    }

    @Required
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
