package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
/*章节管理*/
  private String id;
  private String title;//名称
  private String album_id;//专辑id
  private String size;//大小
  private String duration;//时长
  private String src;//路径
  private String status;
  private String other;
}
