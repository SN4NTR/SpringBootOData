package com.company.springbootodata.service;

import com.company.springbootodata.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(long id);

    void add(User user);

    void delete(long id);

    void edit(User user);
}
