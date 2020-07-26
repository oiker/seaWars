package dushkof.seaWars.services.impl;

import dushkof.seaWars.dao.UserDao;
import dushkof.seaWars.dao.impl.UserDaoImpl;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.StringUtils;

public class UserServiceImpl implements UserService {
    private GameService gameService;
    private UserDao userDao;

    @Override
    public String sayHi() {
        gameService.startGame();
        return "hi";
    }

    @Override
    public String createUser(String name, String password) {
        return getUserDao().createUser(name, password);
    }

    @Override
    public String checkUserPassword(String name, String password) {
        Preconditions.checkArgument(!StringUtils.isEmpty(name), "Argument name cannot be empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(password), "Argument password cannot be empty");

        String truePassword = userDao.getUserPassword(name);
        if (password.equals(truePassword)) {
            return "OK";
        } return "NOK";
    }


    public GameService getGameService() {
        return gameService;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Required
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
