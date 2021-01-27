package com.lms.gj_jewelry.interfaces;

import com.lms.gj_jewelry.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value="/users", produces = "application/json;charset=utf-8")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getUsers() {
        List<User> users = userService.getUsers();

        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
