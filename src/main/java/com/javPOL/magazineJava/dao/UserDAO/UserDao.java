package com.javPOL.magazineJava.dao.UserDAO;

import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Long> {
    Optional<User> findByUsername(String username);
}