package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Counter implements Serializable {
/*计数器*/
  private String id;
  private String title;//名称
  private String count;//数量
  private java.sql.Date lastDate;//最后更新时间
  private String userId;//用户id
  private String taskId;//功课id
  private String status;// 状态
  private String other;//预留字段
}
