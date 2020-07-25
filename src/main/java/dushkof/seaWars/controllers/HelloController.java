package dushkof.seaWars.controllers;

import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "gameService")
    private GameService gameService;

    @GetMapping
    public String list() {
        return userService.sayHi();
    }

    @GetMapping("/init")
    public String init() {
        return gameService.init();
    }

}
