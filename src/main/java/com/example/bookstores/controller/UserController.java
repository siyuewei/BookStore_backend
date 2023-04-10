package com.example.bookstores.controller;


import com.example.bookstores.entity.User;
import com.example.bookstores.repository.UserRepository;
import com.example.bookstores.service.UserService;
import com.example.bookstores.util.request.UserForm.AddUserForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @RequestMapping(value = "api/user/checkUser",method = RequestMethod.GET)
    @CrossOrigin
    public User checkUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.checkUser(username,password);
    }

    @RequestMapping(value = "api/user/add",method = RequestMethod.POST)
    @CrossOrigin
    public boolean addUser(@RequestBody @NotNull AddUserForm addUserForm){
        return userService.addUser(addUserForm);
    }
}
