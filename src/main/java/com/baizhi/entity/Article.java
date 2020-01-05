package com.baizhi.entity;

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
public class Article implements Serializable {
/*文章管理*/
  private String id;
  private String title;//标题
  private String author;//作者
  private String content;//文本编辑器 插图 内容
  private String guruId;//上师id
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private Date createDate;//上传时间
  private String status;
  private String other;
}
