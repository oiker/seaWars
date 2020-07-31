package dushkof.seaWars.services.impl;

import dushkof.seaWars.dao.UserDao;
import dushkof.seaWars.dao.impl.UserDaoImpl;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import java.util.List;

@Configuration
@EnableAutoConfiguration
public class UserServiceImpl implements UserService {
    private GameService gameService;
    private UserDao userDao;

    @Resource
    UserRepo userRepo;

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
        List<User> user = userRepo.findByNameAndPassword(name, password);
        if (user.isEmpty()) {
            return "NOK";
        } return "OK";
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
