package com.baizhi.dao;

import com.baizhi.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserDao {
    List<com.baizhi.entity.MapDto> queryAll();
    Integer tuserDay(@Param("day") Integer day);
    List<com.baizhi.entity.MapDto> queryMonth();
    TUser selectByPhoneNumber(@Param("phone_number") String phone_number);
    void insertTuser(TUser tUser);
}
