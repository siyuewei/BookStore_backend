package com.example.bookstores.dao;

import com.example.bookstores.entity.User;
import com.example.bookstores.entity.UserAuth;

import java.util.Set;

public interface UserDao {
    User getUserById(Long userId);

    String getPasswordByUsername(String username);
    void deleteByUsername(String username);
//    User checkUser(String username, String password);

    User findByUsername(String username);

    void saveUser(User user);
    void saveUserAuth(UserAuth userAuth);
    Set<User> getAllByRole(User.Role role);;

    void changeState(String username, boolean state);

    void updateAvatar(Long userId, String avatar);
}
