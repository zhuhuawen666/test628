package com.qf.service;

import com.qf.mapper.LeaveMapper;
import com.qf.pojo.Leave;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Override
    public int addLeave(Leave leave) {
        leaveMapper.addLeave(leave);
        int lid = leave.getLid();
        //启动流程实例
        long startTime = leave.getStartDate().getTime();
        long endTime = leave.getEndDate().getTime();
        long day = (endTime - startTime) / 1000 / 60 / 60 / 24;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("student",leave.getUser().getUsername());
        map.put("teacher","zhuhuawen1");
        map.put("manager","zhuhuawen2");
        map.put("boss","zhuhuawen3");
        map.put("day",day);
        runtimeService.startProcessInstanceByKey("leave2",lid + "",map);
        //学生完成任务
        String taskid = taskService.createTaskQuery().taskAssignee(leave.getUser().getUsername()).singleResult().getId();
        taskService.complete(taskid);
        return lid;
    }

    @Override
    public List<Leave> leaveList(String username) {
        //待办人物列表
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(username).list();
        List<String> ids = new ArrayList<String>();
        for(Task t : taskList){
            //通过流程实例对象获取业务key 即leave id
            String businessKey = runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult().getBusinessKey();
            ids.add(businessKey);
        }
        List<Leave> leaveList = leaveMapper.leaveList(ids);
        return leaveList;
    }

    @Override
    public int updateLeave(int lid,String username) {

        String taskId = taskService.createTaskQuery().processInstanceBusinessKey(lid + "").taskAssignee(username).singleResult().getId();

        taskService.complete(taskId);
        if (taskService.createTaskQuery().processInstanceBusinessKey(lid + "").singleResult() == null){
            return leaveMapper.updateLeave(lid);
        }
        return 0;


    }
}
