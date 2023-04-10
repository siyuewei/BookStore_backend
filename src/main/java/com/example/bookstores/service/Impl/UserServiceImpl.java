package com.example.bookstores.service.Impl;

import com.example.bookstores.dao.UserDao;
import com.example.bookstores.entity.User;
import com.example.bookstores.entity.UserAuth;
import com.example.bookstores.service.UserService;
import com.example.bookstores.util.request.UserForm.AddUserForm;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void deleteByUsername(String username) {
        userDao.deleteByUsername(username);
    }

    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username, password);
    }

    @Override
    public boolean addUser(AddUserForm addUserForm) {
        if(userDao.findByUsername(addUserForm.getUsername()) != null) {
            return false;
        }
        User user = new User();
        user.setUsername(addUserForm.getUsername());
        user.setName(addUserForm.getName());
        user.setEmail(addUserForm.getEmail());
        user.setAvatar(addUserForm.getAvatar());
        user.setNotes(addUserForm.getNotes());

        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(addUserForm.getUsername());
        userAuth.setPassword(addUserForm.getPassword());

        user.setUserAuth(userAuth);
        userAuth.setUser(user);

        userDao.saveUser(user);
        userDao.saveUserAuth(userAuth);

        return true;
    }
}
