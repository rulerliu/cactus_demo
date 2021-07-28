package com.cloudwise.cactus_demo.mapper.tsb;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloudwise.cactus_demo.pojo.tsb.History;

import java.util.List;

public interface HistoryMapper extends BaseMapper<History> {

    List<History> queryAllHistyries();

}
