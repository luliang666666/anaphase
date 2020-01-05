package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Excel(name ="ID")
    private Integer id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "日期",databaseFormat = "yyyy-MM-dd HH:mm:dd",format = "yyyy-MM-dd")
    private Date birthday;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "状态")
    private String state;
}
