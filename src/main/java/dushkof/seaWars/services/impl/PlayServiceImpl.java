package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.Cell;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.ShipRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.PlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class PlayServiceImpl implements PlayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource
    ShipRepo shipRepo;

    @Resource
    UserRepo userRepo;

    @Resource
    GameRepo gameRepo;

    @Override
    public String checkCellStatus(Long cellId) {
        Cell cell = new Cell(cellId);
        if (cell.isChecked()) {
            return "NOK";
        } cell.setChecked(true);
        List<Cell> cells = shipRepo.getShipByAllCells(cellId);
        System.out.println(cells);
        if (cells.isEmpty()) {
            return "LOSER";
        } //Long shipId = shipRepo.getShipId(cells);
        return null;
    }

    @Override
    public Game surrenderUser(String name, Long gameId) {
        try {
            Game game = gameRepo.findGameById(gameId);
            User user = userRepo.findByName(name);
            game.setFinished(true);
            if (user.equals(game.getUserHost())) {
                game.getSecondUser();
            } game.getUserHost();
            return game;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}