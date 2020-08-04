package dushkof.seaWars.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.FieldRepo;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {

    @Resource
    private GameService gameService;

    @Resource
    UserRepo userRepo;

    @Resource
    private FieldRepo fieldRepo;

    @Resource
    private GameRepo gameRepo;

    @RequestMapping(value = "/createRoom", method = RequestMethod.GET)
    public String createGame(@RequestParam(value = "name") final String name) {
        return gameService.createGame(name);
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String connectSecondUser(@RequestParam(value = "name") final String name,
                                    @RequestParam(value = "id") final Long id) {
        return gameService.connectSecondUser(id, name);
    }

    @RequestMapping(value = "/found", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Game> foundGames() {
        return gameService.foundNewGames();
    }

    @RequestMapping(value = "/getField", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Field getField(@RequestParam(value = "name") final String name,
                           @RequestParam(value = "gameId") final Long gameId) {
        return gameService.createField(name, gameId);
    }

    @RequestMapping(value = "/leave", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String leaveGames(@RequestParam(value = "name") final String name,
                                 @RequestParam(value = "gameId") final Long gameId) {
        return gameService.leaveGame(name, gameId);
    }
}