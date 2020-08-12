package dushkof.seaWars.controllers;

import dushkof.seaWars.Constants;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.impl.GameServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameControllerTest {

    @Resource
    GameService gameService;

    @Resource
    GameRepo gameRepo;

    @Resource
    UserRepo userRepo;

    @Resource
    GameController gameController;

    @Resource
    UserController userController;

    @Test
    void createGameTest() throws IOException {
        try {
            String createUserTest = userController.create("test1", "test1");

            Assert.assertEquals(Constants.OK, createUserTest);

            String createGameTest = gameService.createGame("test1");

            Assert.assertEquals(Constants.OK, createGameTest);

            Game testGame = gameRepo.findByUserHost("test1");
            testGame.setFinished(true);

            Assert.assertEquals(Constants.NOK, createGameTest);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
//            gameRepo.delete(gameRepo.findByUserHost("test1"));
//            userRepo.delete(userRepo.findByName("test1"));

        }
    }

    @Test
    void connectSecondUserTest() {


    }

    @Test
    void foundGames() {
    }

    @Test
    void getField() {
    }

    @Test
    void leaveGames() {
    }

    @Test
    void updateGame() {
    }

    @Test
    void testGetField() {
    }
}