package com.ade.exp.springbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "`user`")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String email;

}