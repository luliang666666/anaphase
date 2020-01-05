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
public class Album implements Serializable {
/*专辑管理*/
  private String id;
  private String title;//标题
  private String img;//头像
  private String score;//分数
  private String author;//作者
  private String broadcaster;//播音员
  private String count;//基数
  private String brief;//简介
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.sql.Date create_date;//发布日期
  private String status;//状态
  private String other;
}
