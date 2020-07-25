package dushkof.seaWars.controllers;

import dushkof.seaWars.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String user(@RequestParam(value = "name") final String name,
                       @RequestParam(value = "password") final String password) {
        return userService.createUser(name, password);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(@RequestParam(value = "name") final String name,
                        @RequestParam(value = "password") final String password) {
        return userService.checkUserPassword(name, password);
    }
}
