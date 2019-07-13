package com.qf.mapper;

import com.qf.pojo.Leave;

import java.util.List;

public interface LeaveMapper {
    int addLeave(Leave leave);
    //老师恩如获取需要审批的请假条列表
    List<Leave> leaveList(List<String> idList);
    int updateLeave(int lid);
}
