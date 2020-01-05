package com.baizhi.server.impl;

import com.baizhi.dao.TUserDao;
import com.baizhi.entity.MapDto;
import com.baizhi.entity.TUser;
import com.baizhi.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    //依赖TUserDao
    @Autowired
    private TUserDao tUserDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MapDto> queryAll() {
        return tUserDao.queryAll();
    }

    @Override
    public List<Integer> tuserDay() {
        ArrayList<Integer> list = new ArrayList<>();
        Integer day1 = tUserDao.tuserDay(1);
        list.add(day1);
        Integer day2 = tUserDao.tuserDay(2);
        if (day2-day1>0){
            list.add(day2-day1);
        }else {
            list.add(0);
        }
        Integer day3 = tUserDao.tuserDay(3);
        if (day3-day2>0){
            list.add(day3-day2);
        }else {
            list.add(0);
        }
        Integer day4 = tUserDao.tuserDay(4);
        if (day4-day3>0){
            list.add(day4-day3);
        }else {
            list.add(0);
        }
        Integer day5 = tUserDao.tuserDay(5);
        if (day5-day4>0){
            list.add(day5-day4);
        }else {
            list.add(0);
        }
        Integer day6 = tUserDao.tuserDay(6);
        if (day6-day5>0){
            list.add(day6-day5);
        }else {
            list.add(0);
        }
        Integer day7 = tUserDao.tuserDay(7);
        if (day7-day6>0){
            list.add(day7-day6);
        }else {
            list.add(0);
        }
        return list;

    }

    @Override
    public List<MapDto> queryMonth() {
        List<MapDto> mapDtos = tUserDao.queryMonth();
        return mapDtos;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String selectByPhoneNumber(String phone_number, TUser tUser) {
        if (tUser == null){
            return "-200";
        }else if (tUser.getPhone_number().equals(phone_number)){
            return "user";
        }else {
            return "密码错误";
        }
    }
}
