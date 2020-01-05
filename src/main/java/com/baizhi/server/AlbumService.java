package com.baizhi.server;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map<String,Object> queryPage(Integer page, Integer rows);
    List<Album> queryAll();
    Album queryById(String id);
}
