package com.baizhi.controller;

import com.baizhi.server.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/album")
public class AlbumController {
    //依赖于AlbumService
    @Autowired
    private AlbumService albumService;
    /*
    rows:每页显示的条数
    page:当前页
    */
    @ResponseBody
    @RequestMapping("queryByPager")
    public Map<String, Object> queryByPager(Integer page, Integer rows){
        Map<String, Object> map = albumService.queryPage(page, rows);
        return map;
    }
}
