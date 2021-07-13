package com.cloudwise.cactus_demo.pojo.cw;

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
@TableName("t_user")
public class User {
    private String id;

    private String typeId;

    private String organizationId;

    private String userName;

    private String fullName;

    private String email;

    private String mobile;

    private Boolean isDisabled;

    private Boolean isDeleted;

    private String oneId;

    private Boolean isLocked;

    private Timestamp createTime;

    private Timestamp updateTime;

}