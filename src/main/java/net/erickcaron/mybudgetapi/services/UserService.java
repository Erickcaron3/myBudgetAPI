package net.erickcaron.mybudgetapi.services;

import net.erickcaron.mybudgetapi.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    List<User> findAll();

    Long create(User user);

    void delete(User user);

    Boolean isUsersInputCorrect(User user);

    void update(Long id, User user);
}
