package com.cloudwise.cactus_demo.controller;

import com.cloudwise.cactus_demo.mapper.cw.UserMapper;
import com.cloudwise.cactus_demo.mapper.tsb.HistoryMapper;
import com.cloudwise.cactus_demo.pojo.cw.User;
import com.cloudwise.cactus_demo.pojo.tsb.History;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @RequestMapping("/getUser")
    public User getUser() {
        User user = userMapper.selectById("d493c1a2ca3a47159683e763ab494af2");
        return user;
    }

    @RequestMapping("/getUserList")
    public List<String> getUserList() {
        List<String> userList = userMapper.queryAllUserIds();
        return userList;
    }

    @RequestMapping("/getHistory")
    public History getHistory() {
        History history = historyMapper.selectById(1);
        return history;
    }

    @RequestMapping("/getHistoryList")
    public List<String> getHistoryList() {
        List<String> userList = historyMapper.queryAllUserIds();
        return userList;
    }

}
