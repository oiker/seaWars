package dushkof.seaWars.repo;

import dushkof.seaWars.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String name);
    User findUserById(Long id);
}
