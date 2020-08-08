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
    public String Shoot(Long cellId) {
        Cell cell = cellRepo.findCellById(cellId);

        if ( cell.isChecked() == true ) {
            return "NOK";
        } else {
            cell.setChecked(true);
            if ( cell.getStatus() == null ) {
                cell.setChecked(true);
                cellRepo.save(cell);
                return "MISS";
            } else {
                Long shipId = jdbcTemplate.queryForObject("SELECT ship_id FROM ship_all_cells WHERE all_cells_id =" + cell.getId() + ";", Long.class);
                List<Cell> allCells = jdbcTemplate.queryForList("SELECT all_cells_id FROM ship_all_cells WHERE ship_id =" + shipId + ";", Cell.class);
                Ship ship = shipRepo.getShipById(shipId);
                ship.setAllCells(allCells);
                List<Cell> woundedCells = ship.getWoundedCells();
                int index = allCells.indexOf(cell);
                woundedCells.add(allCells.get(index));
                ship.setWoundedCells(woundedCells);
                shipRepo.save(ship);
                if ( allCells.size() == woundedCells.size() ) {
                    ship.setAlive(false);
                    shipRepo.save(ship);

                    if (checkIfItWasTheLastShip(cellId)){
                        return "Finish";
                    }
                    else{
                    return "UBIL";}
                } else {
                    return "RANIL";
                }
            }

        }}

    public boolean checkIfItWasTheLastShip(Long cellId){
        Cell cell = cellRepo.findCellById(cellId);
        Long shipId = jdbcTemplate.queryForObject("SELECT ship_id FROM ship_all_cells WHERE all_cells_id =" + cell.getId() + ";",Long.class);
        Ship ship = shipRepo.getShipById(shipId);
        Long fieldId = jdbcTemplate.queryForObject("SELECT field_id FROM field_cells WHERE cells_id =" + cell.getId() + ";", Long.class);
        Field field = fieldRepo.findFieldById(fieldId);
        Game game = gameRepo.findGameById(field.getGameID());
        User user = field.getUser();
        List<Ship> shipsFromShip = jdbcTemplate.queryForList("SELECT ships_id FROM field_ships WHERE field_id =" + field.getId() + ";", Ship.class);
        List<Cell> shipCells = ship.getAllCells();
        fieldRepo.save(field);
        userRepo.save(user);
        if (shipsFromShip.size() == shipCells.size() ) {
            game.setFinished(true);
            game.setWinner(user.getName());
            game.setFinishGame(new Date());
            gameRepo.save(game);
            return true;
        }
        else return false;
    }
}
