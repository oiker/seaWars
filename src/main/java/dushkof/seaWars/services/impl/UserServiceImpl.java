package dushkof.seaWars.services.impl;

import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

@Configuration
@EnableAutoConfiguration
public class UserServiceImpl implements UserService {
    private GameService gameService;

    @Resource
    UserRepo userRepo;

    @Override
    public String sayHi() {
        return "hi";
    }

    @Override
    public String checkUserPassword(String name, String password) {
        try {
            if (userRepo.findByName(name).getPassword().equals(password)) {
                return "OK";
            } else return "NOK";
        } catch (Exception e) {
            e.getMessage();
            return "NOK";
        }
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setGameService(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    public GameService getGameService() {
        return gameService;
    }
}
