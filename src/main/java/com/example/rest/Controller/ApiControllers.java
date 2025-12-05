package com.example.rest.Controller;
import com.example.rest.Models.User;
import com.example.rest.Repo.UserRepo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class ApiControllers {
    private static final Logger log = LoggerFactory.getLogger(ApiControllers.class);

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage(){
        return "Welcome";

    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        log.info("Fetching users");
        return userRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        log.info("Saving user {}", user.getFirstName());
        User savedUser = userRepo.save(user);
        return "Saved";

    }
    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);
        return "Updated";

    }
    @GetMapping("/error")
    public String triggerError(){
        log.error("Error in CRUD operation");
        throw new RuntimeException("Error in CRUD operation");

    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Deleted user ID# " +id;

    }

}
