package com.example.bookstores.dao;

import com.example.bookstores.entity.User;
import com.example.bookstores.entity.UserAuth;

public interface UserDao {
    User getUserById(Long userId);

    String getPasswordByUsername(String username);
    void deleteByUsername(String username);
    User checkUser(String username, String password);

    User findByUsername(String username);

    void saveUser(User user);
    void saveUserAuth(UserAuth userAuth);
}
