package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepo extends JpaRepository<Ship, Long> {

}
