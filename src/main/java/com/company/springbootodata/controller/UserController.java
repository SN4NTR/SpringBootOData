package com.company.springbootodata.controller;

import com.company.springbootodata.entity.User;
import com.company.springbootodata.repository.UserRepository;
import com.company.springbootodata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userService.add(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        userService.edit(user);
    }
}
