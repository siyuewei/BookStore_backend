package com.example.bookstores.service.Impl;
import net.sf.json.JSONObject;
import com.example.bookstores.dao.UserDao;
import com.example.bookstores.entity.User;
import com.example.bookstores.entity.UserAuth;
import com.example.bookstores.service.UserService;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.msg.MsgUtil;
import com.example.bookstores.util.request.UserForm.AddUserForm;
import com.example.bookstores.util.request.UserForm.UpdateUserForm;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Set;



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
    public Msg checkUser(String username, String password) {
        if(userDao.findByUsername(username) == null)
            return MsgUtil.makeMsg(MsgUtil.ERROR,"User not exist");
        if(password.equals(userDao.getPasswordByUsername(username))){
            User user = userDao.findByUsername(username);
            if(user.isStatus()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",user.getId());
                jsonObject.put("username",user.getUsername());
                jsonObject.put("name",user.getName());
                jsonObject.put("email",user.getEmail());
                jsonObject.put("avatar",user.getAvatar());
                jsonObject.put("notes",user.getNotes());
                jsonObject.put("role",user.getRole());
                jsonObject.put("status",user.isStatus());
                return MsgUtil.makeMsg(MsgUtil.SUCCESS,"Login success", jsonObject);
            }
            else
            {
                return MsgUtil.makeMsg(MsgUtil.ERROR,"User is disabled");
            }
        }else {
            return MsgUtil.makeMsg(MsgUtil.ERROR,"Wrong password");
        }
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
        user.setRole(User.Role.CUSTOMER);
        user.setStatus(true);

        UserAuth userAuth = new UserAuth();
//        userAuth.setUsername(addUserForm.getUsername());
        userAuth.setPassword(addUserForm.getPassword());

        user.setUserAuth(userAuth);
        userAuth.setUser(user);

        userDao.saveUser(user);
        userDao.saveUserAuth(userAuth);

        return true;
    }

    @Override
    public void updateUser(UpdateUserForm updateUserForm) {
        User user = userDao.getUserById(updateUserForm.getId());
        user.setName(updateUserForm.getName());
        user.setEmail(updateUserForm.getEmail());
        user.setNotes(updateUserForm.getNotes());
        userDao.saveUser(user);
    }

    @Override
    public Set<User> getAllUser() {
        return userDao.getAllByRole(User.Role.CUSTOMER);
    }

    @Override
    public void changeState(String username, boolean status) {
        userDao.changeState(username, status);
    }

    @Override
    public void updateAvatar(Long userId, String avatar) {
        userDao.updateAvatar(userId,avatar);
    }
}
