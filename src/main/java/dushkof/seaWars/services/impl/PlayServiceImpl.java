package dushkof.seaWars.services.impl;

import dushkof.seaWars.controllers.HelloController;
import dushkof.seaWars.objects.Cell;
import dushkof.seaWars.repo.ShipRepo;
import dushkof.seaWars.services.PlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class PlayServiceImpl implements PlayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource
    ShipRepo shipRepo;

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
}