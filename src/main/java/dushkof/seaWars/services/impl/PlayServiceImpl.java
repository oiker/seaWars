package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.*;
import dushkof.seaWars.repo.FieldRepo;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.ShipRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.PlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;

import javax.annotation.Resource;
import java.util.List;

public class PlayServiceImpl implements PlayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private static final String FIELD_ID_BY_CELLS_ID = "SELECT field_id FROM field_cells WHERE cells_id = %s;";

    @Resource
    ShipRepo shipRepo;

    @Resource
    UserRepo userRepo;

    @Resource
    GameRepo gameRepo;

    @Resource
    FieldRepo fieldRepo;

    @Resource
    JdbcOperations jdbcTemplate;

    @Override
    public String checkCellStatus(Long cellId) {
        Cell cell = new Cell(cellId);
        if (cell.isChecked()) {
            return "NOK";
        } cell.setChecked(true);
        if (cell.getStatus() == null) {
            return "XYI";
        }
        Long fieldId = jdbcTemplate.queryForObject(String.format(FIELD_ID_BY_CELLS_ID, cellId), Long.class);
        Field field = fieldRepo.findFieldById(fieldId);
        List<Ship> ships = field.getShips();
        for (Ship ship : ships)
            System.out.println(ship);
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