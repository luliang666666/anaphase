package com.baizhi.server;

import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    Map<String, Object> queryByPage(Integer page, Integer rows, String id);

    String insert(Chapter chapter);

    void delete(String id);

    void update(Chapter chapter);

    List<Chapter> queryByAlbum_id(String album_id);
}
