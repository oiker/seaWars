package dushkof.seaWars.controllers;


import dushkof.seaWars.form.UserForm;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MainController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Resource
    UserRepo userRepo;

    @Resource
    GameService gameService;

    @Resource
    UserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {"/userList"}, method = RequestMethod.GET)
    public String personList(Model model) {
        List<User> persons = userRepo.findAll();

        model.addAttribute("persons", persons);

        return "personList";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("userForm") UserForm userForm) {

        String name = userForm.getName();
        String password = userForm.getPassword();

        User user = new User(name, password);
        try {
            userRepo.save(user);
            LOGGER.info("User " + name + " is created");
            return "redirect:/userList";
        } catch (Exception e) {
            LOGGER.info("User " + name + " not created");
            LOGGER.info(e.getMessage());
            model.addAttribute("errorMessage", errorMessage);
            return "addPerson";
        }

    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String tryLogin(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "login";
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(Model model,
                        @ModelAttribute("userForm") UserForm userForm){
        String name = userForm.getName();
        String password = userForm.getPassword();
        if (userService.checkUserPassword(name, password) == "OK"){
            LOGGER.info("User " + name + "has logged in");
            return "redirect:/lobby";
        }
        else{
            LOGGER.info("User " + name + " has not logged in");
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = {"/lobby"}, method = RequestMethod.GET)
    public String freeGameList(Model model){
        List<Game> games = gameService.foundNewGames();
        model.addAttribute("games", games);
        return "lobby";
    }


}
