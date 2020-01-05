package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guru implements Serializable {
/*上师管理*/
  private String id;
  private String headImg;//头像
  private String name;//姓名
  private String dharma;//法号
  private String sex; //性别
  private java.sql.Date createDate;  //创建时间
  private String status; //状态
  private String other;
}
