package com.example.bookstores.service;

import com.example.bookstores.entity.User;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.request.UserForm.AddUserForm;
import com.example.bookstores.util.request.UserForm.UpdateUserForm;

import java.util.Set;

public interface UserService {
    void deleteByUsername(String username);
    Msg checkUser(String username, String password);
    boolean addUser(AddUserForm addUserForm);
    void updateUser(UpdateUserForm updateUserForm);
    Set<User> getAllUser();
    void changeState(String username, boolean status);
    void updateAvatar(Long userId, String avatar);
    User getUserByUsername(String username);

}
