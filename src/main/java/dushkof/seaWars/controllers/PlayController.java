package dushkof.seaWars.controllers;

import dushkof.seaWars.objects.Game;
import dushkof.seaWars.repo.FieldRepo;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.services.PlayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("play")
public class PlayController {

    @Resource
    private FieldRepo fieldRepo;

    @Resource
    private GameRepo gameRepo;

    @Resource
    private PlayService playService;

    @RequestMapping(value = "shoot", method = RequestMethod.GET)
    public String shipShoot(@RequestParam(value = "cellId") final Long cellId) {
        return playService.checkCellStatus(cellId);
    }

    @RequestMapping(value = "surrender", method = RequestMethod.GET)
    public String surrender(@RequestParam(value = "name") final String name,
                          @RequestParam(value = "gameId") final Long gameId) {
        return playService.surrenderUser(name, gameId);
    }
}
