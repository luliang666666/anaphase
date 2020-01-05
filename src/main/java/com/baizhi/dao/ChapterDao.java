package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    List<Chapter> queryByPage(@Param("start") Integer start,@Param("rows") Integer rows,@Param("id") String id);
    Integer count();
    void insert(Chapter chapter);
    void delete(@Param("id") String id);
    void update(Chapter chapter);
    List<Chapter> queryById(@Param("album_id") String album_id);
}
