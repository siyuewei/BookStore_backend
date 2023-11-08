package com.example.bookstores.controller;

import com.example.bookstores.entity.User;
import com.example.bookstores.service.UserService;
import com.example.bookstores.service.util.StopWatchService;
import com.example.bookstores.serviceImpl.TokenServiceImpl;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.msg.MsgUtil;
import com.example.bookstores.util.request.UserForm.AddUserForm;
import com.example.bookstores.util.request.UserForm.UpdateUserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import net.sf.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/user")
@Transactional
public class UserController {

    private final UserService userService;

    private final TokenServiceImpl tokenService;

    private final WebApplicationContext applicationContext;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, TokenServiceImpl tokenService, WebApplicationContext applicationContext) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg checkUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
//        return userService.checkUser(username,password);
        User user = userService.getUserByUsername(username);
        if (user == null)
            return MsgUtil.makeMsg(MsgUtil.ERROR, "User not exist", null);

        if (password.equals(user.getUserAuth().getPassword())) {
            if (user.isStatus()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", user.getId());
                jsonObject.put("username", user.getUsername());
                jsonObject.put("name", user.getName() == null ? "" : user.getName());
                jsonObject.put("email", user.getEmail() == null ? "" : user.getEmail());
                jsonObject.put("avatar", user.getAvatar() == null ? "" : user.getAvatar());
                jsonObject.put("notes", user.getNotes() == null ? "" : user.getNotes());
                jsonObject.put("role", user.getRole() == null ? "" : user.getRole());
                jsonObject.put("status", user.isStatus());

                String token = tokenService.getToken(user.getUserAuth());
                jsonObject.put("token", token);
//                System.out.println("controller调用开始计时函数" + this.hashCode() + "\n");
                StopWatchService stopWatchService = applicationContext.getBean(StopWatchService.class);

                stopWatchService.startStopWatch();

                return MsgUtil.makeMsg(MsgUtil.SUCCESS, "Login success", jsonObject);
            } else {
                return MsgUtil.makeMsg(MsgUtil.ERROR, "User is disabled", null);
            }
        } else {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "Wrong password", null);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Msg logout(HttpServletRequest request) {
//        System.out.println("controller调用结束计时函数" + this.hashCode() + "\n");
        StopWatchService stopWatchService = applicationContext.getBean(StopWatchService.class);
        double time = stopWatchService.stopStopWatch();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", time);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "Logout success", jsonObject);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean addUser(@RequestBody @NotNull AddUserForm addUserForm) {
        return userService.addUser(addUserForm);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUser(@RequestBody @NotNull UpdateUserForm updateUserForm) {
        userService.updateUser(updateUserForm);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Set<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public void changeState(@RequestParam("username") String username, @RequestParam("status") boolean status) {
        userService.changeState(username, status);
    }

    @RequestMapping(value = "/updateAvatar", method = RequestMethod.POST)
    public void updateAvatar(@RequestParam("userId") Long userId, @RequestParam("avatar") String avatar) {
        userService.updateAvatar(userId, avatar);
    }
}
