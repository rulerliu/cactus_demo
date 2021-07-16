package com.cloudwise.cactus_demo.mapper.tsb;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloudwise.cactus_demo.pojo.cw.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tsbUserMapper")
public interface UserMapper extends BaseMapper<User> {

    List<String> queryAllUserIds();

}
