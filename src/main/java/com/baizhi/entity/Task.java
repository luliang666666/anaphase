package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {
/*功课管理*/
  private String id;
  private String title;//名称
  private String levels;//级别  （选修 必修）
  private String userId;//用户id
  private java.sql.Date createDate;//发布时间
  private String status;//状态
  private String other;
}
