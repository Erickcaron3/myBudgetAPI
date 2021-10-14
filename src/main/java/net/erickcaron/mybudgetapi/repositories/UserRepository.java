package net.erickcaron.mybudgetapi.repositories;

import net.erickcaron.mybudgetapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String login); //TODO changer pour ExistBy ?
}
