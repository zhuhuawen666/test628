package com.qf.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Profile;
import net.sf.json.JSONObject;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@SessionAttributes(names = {"user"})
@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserService userService;
    @RequestMapping("st1")
    public String start(){
        return "login";
    }
    @RequestMapping("st2")
    public String start2(){
        return "register";
    }

//    @RequestMapping("login")
//    public String login(Model model,String username, String password){
//            User user = userService.login(username, password);
//            model.addAttribute("user",user);
//            PageHelper.startPage(1,3);
//            List<Profile> list = userService.getProfileAll(user.getUid());
//            PageInfo<Profile> pageInfo = new PageInfo(list);
//            model.addAttribute("pageInfo",pageInfo);
//            return "list";
//
//
//
//    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username,String password,Model model){
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("这是user：delete" + subject.isPermitted("user:delete"));
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try{
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()){
                User user = userService.login(username, password);
                model.addAttribute("user",user);
                PageHelper.startPage(1,3);
                List<Profile> list = userService.getProfileAll(user.getUid());
                PageInfo<Profile> pageInfo = new PageInfo(list);
                model.addAttribute("pageInfo",pageInfo);
                return "list";
            }
        }catch (AuthenticationException e){
            System.out.println("登陆失败");
        }
        return "login";
    }

    @RequestMapping("register")
    public String register(String username,String password){
       int i= userService.register(username,password);

       if (i == 0){
           return "register";
       }else {
           return "login";
       }

    }
    @RequestMapping("list")
    public String list(Model model,@RequestParam(defaultValue = "1") int pageNum,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        PageHelper.startPage(pageNum,3);
        List list = userService.getProfileAll(user.getUid());
        PageInfo<Profile> pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        return "list";
    }
    @RequestMapping(value = "aaa",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findUser(String username) {
        JSONObject jsonObject = new JSONObject();
        if (username.equals("")){

            jsonObject.element("resp", "false");
            return jsonObject.toString();
        }
        Integer i = userService.findUser(username);
        System.out.println(i);
        if (i != null) {

            jsonObject.element("resp", "false");

            return jsonObject.toString();
        } else {

            jsonObject.element("resp", "true");
            return jsonObject.toString();
        }

    }
    @RequestMapping("logout")
    public String logout(HttpServletRequest req){
        req.getSession().removeAttribute("user");
        return "login";
    }
    @RequestMapping(value = "login2",produces = "html/text;charset=utf-8")
    @ResponseBody
    public String login2(String username,String password){
        User user = userService.login(username,password);
        if (user != null){
            return "登陆成功";
        }else {
            return "登陆失败";
        }
    }
    @RequestMapping(value = "/abc")
    public  String bbb(){

        return "fff";
    }
}
