package com.cloudwise.cactus_demo.mapper.cw;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloudwise.cactus_demo.pojo.cw.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<String> queryAllUserIds();

}
