package dushkof.seaWars.repo;

import dushkof.seaWars.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String name);
}
