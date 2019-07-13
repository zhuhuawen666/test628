package com.qf.service;

import com.qf.pojo.Leave;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface LeaveService {
    int addLeave(Leave leave);
    List<Leave> leaveList(String username);
    int updateLeave(int lid,String username);
}
