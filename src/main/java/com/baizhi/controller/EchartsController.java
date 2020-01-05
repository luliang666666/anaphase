package com.baizhi.controller;

import com.baizhi.entity.MapDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @RequestMapping("getMapEcharts")
    public List<Map<String,Object>> getMapEcharts(){
        List<Map<String,Object>> list = new ArrayList<>();
        List<MapDto> list1 = new ArrayList<>();
        list1.add(new MapDto("北京",new Random().nextInt(1000)));
        list1.add(new MapDto("天津",new Random().nextInt(1000)));
        list1.add(new MapDto("上海",new Random().nextInt(1000)));
        list1.add(new MapDto("重庆",new Random().nextInt(1000)));
        list1.add(new MapDto("贵州",new Random().nextInt(1000)));
        list1.add(new MapDto("云南",new Random().nextInt(1000)));
        list1.add(new MapDto("四川",new Random().nextInt(1000)));
        list1.add(new MapDto("湖南",new Random().nextInt(1000)));
        list1.add(new MapDto("广西",new Random().nextInt(1000)));
        list1.add(new MapDto("西藏",new Random().nextInt(1000)));
        list1.add(new MapDto("新疆",new Random().nextInt(1000)));
        list1.add(new MapDto("青海",new Random().nextInt(1000)));
        list1.add(new MapDto("甘肃",new Random().nextInt(1000)));
        list1.add(new MapDto("宁夏",new Random().nextInt(1000)));
        list1.add(new MapDto("湖北",new Random().nextInt(1000)));
        list1.add(new MapDto("广东",new Random().nextInt(1000)));
        list1.add(new MapDto("海南",new Random().nextInt(1000)));
        list1.add(new MapDto("福建",new Random().nextInt(1000)));
        list1.add(new MapDto("江苏",new Random().nextInt(1000)));
        list1.add(new MapDto("河北",new Random().nextInt(1000)));
        list1.add(new MapDto("河南",new Random().nextInt(1000)));
        list1.add(new MapDto("山东",new Random().nextInt(1000)));
        list1.add(new MapDto("山西",new Random().nextInt(1000)));
        list1.add(new MapDto("辽宁",new Random().nextInt(1000)));
        list1.add(new MapDto("黑龙江",new Random().nextInt(1000)));
        list1.add(new MapDto("吉林",new Random().nextInt(1000)));
        list1.add(new MapDto("内蒙古",new Random().nextInt(1000)));
        list1.add(new MapDto("陕西",new Random().nextInt(1000)));
        list1.add(new MapDto("江西",new Random().nextInt(1000)));
        list1.add(new MapDto("台湾",new Random().nextInt(1000)));
        list1.add(new MapDto("香港",new Random().nextInt(1000)));
        list1.add(new MapDto("澳门",new Random().nextInt(1000)));
        list1.add(new MapDto("浙江",new Random().nextInt(1000)));
        list1.add(new MapDto("安徽",new Random().nextInt(1000)));
        for (MapDto mapDto : list1) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",mapDto.getName());
            map.put("value",mapDto.getValue());
            list.add(map);
        }
        return list;
    }
}
