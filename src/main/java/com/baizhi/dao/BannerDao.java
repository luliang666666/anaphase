package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    List<Banner> queryPage(@Param("start") Integer start, @Param("rows") Integer rows);
    void insert(Banner banner);
    void delete(String[] ids);
    void update(Banner banner);
    Integer count();
    List<Banner> query();

}
