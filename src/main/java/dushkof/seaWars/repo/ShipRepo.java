package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Ship;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipRepo extends JpaRepository<Ship, Long> {
    Ship findShipById (Long shipId);
}
