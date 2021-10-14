package net.erickcaron.mybudgetapi.rest;

import lombok.AllArgsConstructor;
import net.erickcaron.mybudgetapi.exceptions.ResourceError;
import net.erickcaron.mybudgetapi.exceptions.ResourceException;
import net.erickcaron.mybudgetapi.models.User;
import net.erickcaron.mybudgetapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Transactional
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        checkFound(userService.findById(id));
        return userService.findById(id).get();
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public Long create(@RequestBody @Valid User user) {
        checkInput(user);
        return userService.create(user);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid User user){
        checkFound(userService.findById(id));
        checkInput(user);
        userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        checkFound(userService.findById(id));
        userService.delete(userService.findById(id).get());
    }

    private void checkFound(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new ResourceException(ResourceError.RESOURCE_NOT_FOUND);
        }
    }

    private void checkInput(User user){
        if(!userService.isUsersInputCorrect(user)){
            throw new ResourceException(ResourceError.INCORRECT_INPUT);
        };
    }


}
