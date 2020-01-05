package com.baizhi.server;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    Map<String,Object> queryPage(Integer page, Integer rows);
    String insert(Banner banner);
    void delete(String[] id);
    void update(Banner banner);
    List<Banner>query();
}
