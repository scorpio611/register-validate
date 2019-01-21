package com.codegym.service;

import com.codegym.model.User;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface UserService {
    Iterable<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);
}
