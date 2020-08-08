package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepo extends JpaRepository<Field, Long> {
    Field findFieldByCells(Long CellId);
    Field findFieldById(Long FieldId);
    Field findFieldByUser(Long UserId);
}
