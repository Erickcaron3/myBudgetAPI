package net.erickcaron.mybudgetapi.services.impl;

import lombok.AllArgsConstructor;
import net.erickcaron.mybudgetapi.models.User;
import net.erickcaron.mybudgetapi.repositories.UserRepository;
import net.erickcaron.mybudgetapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Long create(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Boolean isUsersInputCorrect(User user) {
        return user.getEmail() != null && user.getPassword() != null && user.getFirstName() != null && user.getLastName() != null;
    }

    @Override
    public void update(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }
}
