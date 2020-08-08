package dushkof.seaWars.services.impl;

import com.mysql.cj.xdevapi.Collection;
import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.*;
import dushkof.seaWars.repo.*;
import dushkof.seaWars.services.PlayService;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PlayServiceImpl implements PlayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private static final String FIELD_ID_BY_CELLS_ID = "SELECT field_id FROM field_cells WHERE cells_id = %s;";
    private static final String SHIP_ID_BY_CELLS_ID = "SELECT ship_id FROM ship_all_cells WHERE all_cells_id = %s;";
    private static final String ADD_SHIP_WOUNDED_CELLS = "INSERT INTO ship_wounded_cells (ship_id, wounded_cells_id) VALUES (%s, %s);";
    private static final String ALL_CELLS_BY_SHIP = "SELECT all_cells_id FROM ship_all_cells WHERE ship_id = %s;";
    private static final String ALL_WOUNDED_CELL_BY_SHIP = "SELECT wounded_cells_id FROM ship_wounded_cells WHERE ship_id = %s;";
    private static final String ALL_SHIPS_BY_FIELD = "SELECT ships_id FROM field_ships WHERE field_id = %s;";
    List<Long> killedShips = new LinkedList<>();

    @Resource
    ShipRepo shipRepo;

    @Resource
    UserRepo userRepo;

    @Resource
    GameRepo gameRepo;

    @Resource
    FieldRepo fieldRepo;

    @Resource
    CellRepo cellRepo;

    @Resource
    JdbcOperations jdbcTemplate;

    @Override
    public String checkCellStatus(Long cellId) {
        Cell cell = cellRepo.findCellById(cellId);
        if (cell.isChecked()) {
            return "NOK";
        } cell.setChecked(true);
        cellRepo.save(cell);
        if (cell.getStatus() == null) {
            return "MISS";
        }
        Long shipId = jdbcTemplate.queryForObject(String.format(SHIP_ID_BY_CELLS_ID, cellId), Long.class);
        jdbcTemplate.execute(String.format(ADD_SHIP_WOUNDED_CELLS, shipId, cellId));
        List<Long> allCellByShips = jdbcTemplate.queryForList(String.format(ALL_CELLS_BY_SHIP, shipId), Long.class);
        List<Long> allWoundedCellByShip = jdbcTemplate.queryForList(String.format(ALL_WOUNDED_CELL_BY_SHIP, shipId), Long.class);
        Ship ship = shipRepo.findShipById(shipId);
        Long fieldId = jdbcTemplate.queryForObject(String.format(FIELD_ID_BY_CELLS_ID, cellId), Long.class);
        List<Long> allShipsByField = jdbcTemplate.queryForList(String.format(ALL_SHIPS_BY_FIELD, fieldId), Long.class);
        Field field = fieldRepo.findFieldById(fieldId);
        Game game = gameRepo.findGameById(field.getGameID());
        if (allWoundedCellByShip.equals(allCellByShips)) {
            killedShips.add(shipId);
            ship.setAlive(false);
            int num = allShipsByField.size();
            int count = 0;
            for (int i = 0; i < killedShips.size(); i++) {
                for (int k = 0; k < allShipsByField.size(); k++) {
                    if (killedShips.get(i).equals(allShipsByField.get(k)) == true) {
                        count++;
                        if (num == count) {
                            game.setFinished(true);
                            game.setWinner(field.getUser().getName());
                            game.setFinishGame(new Date());
                            gameRepo.save(game);
                            return "FINISH";
                        }
                    }
                }
            }
            return "YBIL";
        }
            return "RANIL";
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