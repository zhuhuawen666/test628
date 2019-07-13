package com.qf.service;

import com.qf.mapper.UserMapper;
import com.qf.pojo.Profile;
import com.qf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.getUser(username,password);
        return user;
    }

    @Override
    public int register(String username, String password) {
        User user = userMapper.getUser(username,password);
        if (user == null){
            userMapper.insertUser(username,password);
            return 1;
        }else {
            return 0;
        }


    }

    @Override
    public List<Profile> getProfileAll(int uid) {
        return userMapper.getProfileAll(uid);
    }

    @Override
    public Integer findUser(String username) {
        return userMapper.findUser(username);
    }

    @Override
    public String getPassword(String password) {

        return userMapper.getPassword(password);
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        return userMapper.getRolesByUsername(username);
    }

    @Override
    public List<String> getPermissionByRole(String username) {
        List<String> roles = userMapper.getRolesByUsername(username);
        List<String> permissions = userMapper.getPermissionByRole(roles);
        return permissions;
    }


}
