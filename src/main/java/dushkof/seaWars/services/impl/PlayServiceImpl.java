package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.*;
import dushkof.seaWars.repo.*;
import dushkof.seaWars.services.PlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayServiceImpl implements PlayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private static final String GET_SHIP_ID_BY_CELL_ID = "SELECT ship_id FROM ship_all_cells WHERE all_cells_id = %s;";
    private static final String GET_FIELD_BY_CELL_ID = "SELECT field_id FROM field_cells WHERE cells_id = %s;";

    @Resource
    ShipRepo shipRepo;

    @Resource
    UserRepo userRepo;

    @Resource
    FieldRepo fieldRepo;

    @Resource
    CellRepo cellRepo;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    GameRepo gameRepo;

    @Override
    public String whoseTurn(String playerName, Long gameId){
        Game game = gameRepo.findGameById(gameId);
        if (game.getFinished() && !game.getWinner().equals(playerName)){
            return "LOSE";
        }
        if (game.getWhoseTurn().equals(playerName)){
            return "OK";
        }
        return "NOK";
    }

    @Override
    public String shoot(Long cellId) {
        Cell cell = cellRepo.findCellById(cellId);

        if (cell == null || cell.isChecked()) {
            return "NOK";
        }
        cell.setChecked(true);
        if ( cell.getStatus() == null ) {
            cell.setChecked(true);
            cellRepo.save(cell);
            return "MISS";
        }
        Long shipId = jdbcTemplate.queryForObject(String.format(GET_SHIP_ID_BY_CELL_ID, cell.getId()), Long.class);
        Ship ship = shipRepo.getShipById(shipId);
        List<Cell> allCells = ship.getAllCells();
        List<Cell> woundedCells = ship.getWoundedCells();
        woundedCells.add(cell);
        ship.setWoundedCells(woundedCells);
        shipRepo.save(ship);
        if ( allCells.size() == woundedCells.size() ) {
            ship.setAlive(false);
            shipRepo.save(ship);

            if ( checkIfItWasTheLastShip(cellId) ) {
                return "Finish";
            } else {
                return "UBIL";
            }
        } else {
            return "RANIL";
        }


    }

    public boolean checkIfItWasTheLastShip(Long cellId) {

        Long fieldId = jdbcTemplate.queryForObject(String.format(GET_FIELD_BY_CELL_ID,cellId), Long.class);
        Field field = fieldRepo.getOne(fieldId);
        return (field.getShips().stream().anyMatch(ship -> ship.isAlive()) ? false : setWinner(field));
    }

    private boolean setWinner(Field field) {
        Game game = gameRepo.findGameById(field.getGameID());
        User user = field.getUser();
        game.setFinished(true);
        game.setWinner(user.getName());
        game.setFinishGame(new Date());
        gameRepo.save(game);
        return true;
    }

    @Override
    public String surrenderUser(String name, Long gameId) {
        try {
            Game game = gameRepo.findGameById(gameId);
            User user = userRepo.findByName(name);
            game.setFinished(true);
            if (user.equals(game.getUserHost())) {
                game.setWinner(game.getSecondUser().getName());
            } game.setWinner(game.getUserHost().getName());
            gameRepo.save(game);
            return "OK";
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return "NOK";
        }
    }
}