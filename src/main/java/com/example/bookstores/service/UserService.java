package com.example.bookstores.service;

import com.example.bookstores.entity.User;
import com.example.bookstores.util.request.UserForm.AddUserForm;

public interface UserService {
    void deleteByUsername(String username);
    User checkUser(String username, String password);
    boolean addUser(AddUserForm addUserForm);
}
