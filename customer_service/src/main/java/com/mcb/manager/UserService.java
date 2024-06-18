package com.mcb.manager;

import com.mcb.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    User saveUser(User user);

    void delete(Long id);

    List<User> findAll();
}
