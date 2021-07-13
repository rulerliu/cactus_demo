package com.cloudwise.cactus_demo.pojo.tsb;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_history")
public class History {
    private Long id;

    private String serviceId;

    private String bimRequestId;

    private String name;

    private Integer type;

    private String operateType;

    private String content;

    private String result;

    private Timestamp createTime;

}