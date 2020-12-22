package com.springblog.webblog.service;

import com.springblog.webblog.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    User save(User user);

}
