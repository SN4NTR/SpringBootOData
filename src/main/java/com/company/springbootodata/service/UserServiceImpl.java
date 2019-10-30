package com.company.springbootodata.service;

import com.company.springbootodata.entity.constant.Role;
import com.company.springbootodata.entity.User;
import com.company.springbootodata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void add(User user) {
        user.setCreatedAt(new Date());
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(userRepository.getOne(id));
    }

    @Override
    public void edit(User user) {
        user.setRole(userRepository.getOne(user.getId()).getRole());
        user.setCreatedAt(userRepository.getOne(user.getId()).getCreatedAt());
        userRepository.save(user);
    }
}
