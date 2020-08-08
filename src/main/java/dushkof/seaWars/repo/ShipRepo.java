package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Cell;
import dushkof.seaWars.objects.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipRepo extends JpaRepository<Ship, Long> {
    Ship getShipById(Long shipId);
}
