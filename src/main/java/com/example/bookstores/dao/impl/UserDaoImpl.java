package com.example.bookstores.dao.impl;

import com.example.bookstores.dao.UserDao;
import com.example.bookstores.entity.User;
import com.example.bookstores.entity.UserAuth;
import com.example.bookstores.repository.UserAuthRepository;
import com.example.bookstores.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;

    public UserDaoImpl(UserRepository userRepository, UserAuthRepository userAuthRepository) {
        this.userRepository = userRepository;
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public String getPasswordByUsername(String username) {
        return userAuthRepository.getByUsername(username).getPassword();
    }

    @Override
    public void deleteByUsername(String username) {
        userAuthRepository.deleteByUsername(username);
        userRepository.deleteByUsername(username);
    }

    @Override
    public User checkUser(String username, String password) {
        UserAuth userAuth = userAuthRepository.getByUsername(username);
        if (userAuth == null) {
            return null;
        }
        if (userAuth.getPassword().equals(password)) {
            return userAuth.getUser();
        }else {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveUserAuth(UserAuth userAuth) {
        userAuthRepository.save(userAuth);
    }
}
