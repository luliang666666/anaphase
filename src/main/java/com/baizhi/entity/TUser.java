package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUser implements Serializable {

  private String id;   //ID
  private String phone_number;   //电话号码
  private String password;  //密码
  private String name;      //姓名
  private String dharma;    //法号
  private String head_img;  //头像
  private String sex;   //性别
  private String address;  //地址
  private String sign;  //签名
  private String guru_id;  //上师id
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.sql.Date last_date;  //登录日期
  private java.sql.Date create_date;  //上传时间
  private String status;   //状态
  private String salt;  //私盐
  private String other;
}
