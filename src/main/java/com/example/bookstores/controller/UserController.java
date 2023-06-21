package com.example.bookstores.controller;

import net.sf.json.JSONObject;
import com.example.bookstores.entity.User;
import com.example.bookstores.service.UserService;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.request.UserForm.AddUserForm;
import com.example.bookstores.util.request.UserForm.UpdateUserForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/user")
@Transactional
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/checkUser",method = RequestMethod.GET)
    public Msg checkUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.checkUser(username,password);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public boolean addUser(@RequestBody @NotNull AddUserForm addUserForm){
        return userService.addUser(addUserForm);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateUser(@RequestBody @NotNull UpdateUserForm updateUserForm){
        userService.updateUser(updateUserForm);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public Set<User> getAllUser(){
        return userService.getAllUser();
    }

    @RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
    public void changeState(@RequestParam("username") String username, @RequestParam("status") boolean status){
        userService.changeState(username,status);
    }

    @RequestMapping(value = "/updateAvatar",method = RequestMethod.POST)
    public void updateAvatar(@RequestParam("userId") Long userId, @RequestParam("avatar") String avatar){
        userService.updateAvatar(userId,avatar);
    }
}
