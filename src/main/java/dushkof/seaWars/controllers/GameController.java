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


    @RequestMapping(value = "/getfield", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getField(@RequestBody String json) throws JsonProcessingException {
        Field field = new Gson().fromJson(json, Field.class);
        fieldRepo.save(field);
        return "hui";
    }

}
