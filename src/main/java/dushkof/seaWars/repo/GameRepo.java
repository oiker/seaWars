package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<Game, Long> {
    Game findGameById(Long id);
}
