package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.server.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {
    //依赖于BannerService
    @Autowired
    private BannerService bannerService;
    /*
    rows:每页显示的条数
    page:当前页
    */
    @ResponseBody
    @RequestMapping("queryPager")
    public Map<String, Object> queryPager(Integer page, Integer rows){
        Map<String, Object> map = bannerService.queryPage(page,rows);
        return map;
    }
    @ResponseBody
    @RequestMapping("query")
    public List<Banner> query(){
        List<Banner> query = bannerService.query();
        return query;
    }
    //文件上传
    @ResponseBody
    @RequestMapping("upload")
    public void upload(MultipartFile img,String bannerId, HttpSession session){

        //判断上传文件是否存在
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String originalFilename = img.getOriginalFilename();
        String str = new Date().getTime()+"_"+originalFilename;

        try {
            img.transferTo(new File(realPath,str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建一个banner对象用于接收id 和图片名
        Banner banner=new Banner();
        //设置id
        banner.setId(bannerId);
        //设置文件名
        banner.setImg(str);
        bannerService.update(banner);

    }

    @ResponseBody
    @RequestMapping("add")
    public  Map<String,Object> edit(String oper, Banner banner, String[] id) {
       Map<String,Object> map=new HashMap<>();
        //oper=add 添加  oper=edit 修改 oper=del 删除
        if ("add".equals(oper)) {
            String insert = bannerService.insert(banner);
            map.put("bannerId",insert);
            return map;
        } else if ("edit".equals(oper)) {
            if (banner.getImg()==""){
                banner.setImg(null);
                bannerService.update(banner);
            }else {
                bannerService.update(banner);
                map.put("bannerId",banner.getId());
            }
        } else {
            bannerService.delete(id);
        }
        return map;
    }


}
