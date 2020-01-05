package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner implements Serializable {
  @Excel(name = "ID",width = 20,height = 50)
  private String id;
  @Excel(name = "名称")
  private String title;  //图片名称
  @Excel(name = "图片",type = 2,width = 20,height = 50)
  private String img;   //图片路径
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "日期",width = 20,height = 50,format = "yyyy-MM-dd",databaseFormat = "yyyy-MM-dd HH:mm:ss")
  private Date create_date;  //上传时间
  @Excel(name = "状态")
  private String status; //状态
  @Excel(name = "预留字段")
  private String other;  //预留字段
}
