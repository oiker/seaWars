package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.UserController;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.springframework.beans.factory.annotation.Required;

public class UserServiceImpl implements UserService {
    private GameService gameService;

    @Override
    public String sayHi() {
        gameService.startGame();
        return "hi";
    }

    @Override
    public String userData(String name, String password) {
        return name + password;
    }

    public GameService getGameService() {
        return gameService;
    }

    @Required
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}
