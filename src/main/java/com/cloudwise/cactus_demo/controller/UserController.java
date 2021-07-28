package com.cloudwise.cactus_demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudwise.cactus_demo.mapper.cw.UserMapper;
import com.cloudwise.cactus_demo.mapper.tsb.HistoryMapper;
import com.cloudwise.cactus_demo.pojo.PageVo;
import com.cloudwise.cactus_demo.pojo.cw.User;
import com.cloudwise.cactus_demo.pojo.tsb.History;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired
    @Qualifier("tsbUserMapper")
    com.cloudwise.cactus_demo.mapper.tsb.UserMapper tsbUserMapper;

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

    /**------ db2 ---------**/
    @RequestMapping("/getUserList2")
    public List<User> getUserList2() {
        List<User> user = tsbUserMapper.queryAllUsers();
        return user;
    }

    @RequestMapping("/getHistory")
    public History getHistory() {
        History history = historyMapper.selectById(1);
        return history;
    }

    @RequestMapping("/getHistoryList")
    public List<History> getHistoryList() {
        List<History> userList = historyMapper.queryAllHistyries();
        return userList;
    }

    @RequestMapping("/getHistoryPage")
    public PageVo<History> getHistoryList2(Integer current, Integer size) {
        IPage<History> userPage = new Page<>(current, size); //参数一是当前页，参数二是每页个数
        userPage = historyMapper.selectPage(userPage, null);
        return PageVo.pageResult(userPage);
    }

}
