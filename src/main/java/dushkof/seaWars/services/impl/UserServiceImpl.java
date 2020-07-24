package dushkof.seaWars.services.impl;

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

    public GameService getGameService() {
        return gameService;
    }

    @Required
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}
