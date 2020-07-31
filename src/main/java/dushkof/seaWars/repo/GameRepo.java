package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepo extends JpaRepository<Game, Long> {
    List<Game> findByUserHost (User name);
    Game findGameById(Long id);
}
