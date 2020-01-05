package com.baizhi.controller;

import com.baizhi.entity.MapDto;
import com.baizhi.entity.TUser;
import com.baizhi.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    //依赖UserService
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("userQuery")
    public List<MapDto> userQuery(){
        List<MapDto> list = userService.queryAll();
        /*for (MapDto mapDto : list) {
            System.out.println(mapDto);
        }*/
        return list;
    }
    @ResponseBody
    @RequestMapping("day")
    public List<Integer> day(){
        List<Integer> integers = userService.tuserDay();
        return integers;
    }
    @ResponseBody
    @RequestMapping("queryMonth")
    public List<MapDto> queryMonth(){
        List<MapDto> mapDtos = userService.queryMonth();
       /* for (MapDto mapDto : mapDtos) {
            System.out.println();
        }*/

        return mapDtos;
    }
    @ResponseBody
    @RequestMapping("login")
    public String login(String phone_number, TUser tUser){
        String s = userService.selectByPhoneNumber(phone_number, tUser);
        return s;
    }
}
