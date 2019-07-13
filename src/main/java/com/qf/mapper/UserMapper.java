package com.qf.mapper;

import com.qf.pojo.Profile;
import com.qf.pojo.User;
import org.apache.ibatis.annotations.Param;
import sun.awt.SunHints;

import java.util.List;

public interface UserMapper {
    User getUser(@Param(value = "username")String  username, @Param(value = "password") String password);
    int insertUser(@Param(value = "username")String  username, @Param(value = "password") String password);
    List<Profile> getProfileAll(int uid);
    Integer findUser(String username);
    String getPassword(String password);
    List<String> getRolesByUsername(String username);
    List<String> getPermissionByRole(List<String> roles);
}
