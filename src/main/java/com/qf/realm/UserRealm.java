package com.qf.realm;

import com.qf.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前账户用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查询数据库中的角色账户表，获取到该账户对应的角色表
        Set<String> roles = new HashSet<String>();
        roles.add("role1");
        //根据角色列表分别从数据库的角色权限表中查询出该角色下的对应的权限列表
        Set<String> permissions = new HashSet<String>();
//        permissions.add("user:add");
        permissions.add("user:update");
       permissions.add("user:delete");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        //数据库中根据用户名查询密码
        String password = userService.getPassword(username);

        SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(username,password,"UserRealm");
        return simpleAuthorizationInfo;
    }
}
