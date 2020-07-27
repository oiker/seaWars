package dushkof.seaWars.controllers;

import dushkof.seaWars.objects.Game;
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

    @RequestMapping(value = "/host", method = RequestMethod.GET)
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
}
