package com.baizhi.server;

import com.baizhi.entity.MapDto;
import com.baizhi.entity.TUser;

import java.util.List;

public interface UserService {
    List<MapDto> queryAll();
    List<Integer> tuserDay();
    List<MapDto> queryMonth();
    String selectByPhoneNumber(String phone_number, TUser tUser);
}
