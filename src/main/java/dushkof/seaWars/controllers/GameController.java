package dushkof.seaWars.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.services.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {

    @Resource
    private GameService gameService;

    @RequestMapping(value = "/createRoom", method = RequestMethod.GET)
    public String createGame(@RequestParam(value = "name") final String name) {
        return gameService.createGame(name);
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String connectSecondUser(@RequestParam(value = "name") final String name,
                                    @RequestParam(value = "id") final Integer id) {
        return gameService.connectSecondUser(id, name);
    }

    @RequestMapping(value = "/found", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Game> foundGames() {
        return gameService.foundNewGames();
    }


    @RequestMapping(value = "/getfield", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Field getField(@RequestBody String json) throws JsonProcessingException {
        Field field = new Gson().fromJson(json, Field.class);
        return null;
    }

}
