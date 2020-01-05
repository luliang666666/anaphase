package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    List<Album> queryPage(@Param("start") Integer start, @Param("rows") Integer rows);
    Integer count();
    List<Album> queryAll();
    Album selectById(@Param("id") String id);
}
