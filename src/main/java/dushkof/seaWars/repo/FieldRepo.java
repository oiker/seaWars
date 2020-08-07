package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Cell;
import dushkof.seaWars.objects.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepo extends JpaRepository<Field, Long> {
    Field findFieldById (Long fieldId);
}
