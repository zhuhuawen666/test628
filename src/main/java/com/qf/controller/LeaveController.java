package com.qf.controller;

import com.qf.pojo.Leave;
import com.qf.pojo.User;
import com.qf.service.LeaveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LeaveController {
    @Autowired
    private LeaveService leaveService;
    @RequiresPermissions(value = "user:add")
    @RequestMapping("/leave")
    public String leave(){
        return "leave";
    }
    @RequestMapping("/addLeave")
    public String addLeave(Leave leave, HttpSession session){
        User user = (User) session.getAttribute("user");
        leave.setUser(user);
        int i = leaveService.addLeave(leave);
        return "redirect:/user/list";
    }
    @RequestMapping("/leaveList")
    public String leaveList(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        String username = user.getUsername();
        List<Leave> leaveList = leaveService.leaveList(username);
        model.addAttribute("leaveList",leaveList);
        return "leavelist";
    }
    @RequestMapping("/updateleave")
    @ResponseBody
    public String updateLeave(int lid,HttpSession session){
        User user = (User) session.getAttribute("user");
        leaveService.updateLeave(lid,user.getUsername());
        return "success";
    }

}
