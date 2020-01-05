package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.server.ChapterService;
import com.baizhi.util.VideoTimeUtil;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    //依赖于ChapterSerivce
    @Autowired
    private ChapterService chapterService;
    @ResponseBody
    @RequestMapping("queryByPage")
    public Map<String,Object> queryByPage(Integer page, Integer rows,String id){
        Map<String, Object> map = chapterService.queryByPage(page, rows,id);
        return map;
    }
    @ResponseBody
    @RequestMapping("edit")
    public Map<String,Object>edit(String oper, Chapter chapter, String id,String fuid,HttpSession session){
        HashMap<String, Object> map = new HashMap<>();
        //oper=add 添加  oper=edit 修改 oper=del 删除
        if (oper.equals("add")){
            //设置父id
            chapter.setAlbum_id(fuid);
            String insert = chapterService.insert(chapter);
            map.put("bannerId",insert);
            return map;
        }else if (oper.equals("edit")){
            if (chapter.getSrc()==""){
                chapter.setSrc(null);
                chapterService.update(chapter);
            }else {
                chapterService.update(chapter);
                map.put("bannerId",chapter.getId());
            }
        }else {
            //通过相对路径获取绝对路径
            /*String realPath = session.getServletContext().getRealPath("/mp3");
            File file = new File(realPath);*/
            chapterService.delete(id);
        }
        return map;
    }

    //文件上传
    @ResponseBody
    @RequestMapping("upload")
    public void upload(MultipartFile src, HttpSession session,String bannerId){
        //判断上传文件是否存在
        String realPath = session.getServletContext().getRealPath("/mp3");

        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String filename = src.getOriginalFilename();

        String str = new Date().getTime()+filename;
        //创建一个chapter对象用于接收id 和MP3名
        Chapter chapter = new Chapter();
        //设置id
        chapter.setId(bannerId);
        //设置文件名
        chapter.setSrc(str);
        /*//调用修改方法
        chapterService.update(chapter);*/
        //上传到文件
        try {
            src.transferTo(new File(realPath, str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取到文件大小
        Long len = src.getSize();
        String printSize = getPrintSize(len);
        chapter.setSize(printSize);

        //获取到文件的时长
        Encoder encoder = new Encoder();
        long ls = 0;
        MultimediaInfo m;
        File file1 = new File(realPath,chapter.getSrc());
        try {
            m = encoder.getInfo(file1);
            ls = m.getDuration()/1000;
            String format = VideoTimeUtil.formatTime(ls);
            chapter.setDuration(format);
        } catch (Exception e) {
            System.out.println("获取音频时长有误：" + e.getMessage());
        }

        chapterService.update(chapter);
    }
    public  String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }



}
    /*文件下载*/
    @ResponseBody
    @RequestMapping("download")
    public String download(String fileName, HttpSession session, HttpServletResponse response){
        //接收数据  文件标识  文件名
        //调用业务 从服务器上获取对应的文件  然后响应给客户端
        //获取文件路径（通过相对路径获取绝对路径） io输入流
        String realPath = session.getServletContext().getRealPath("/mp3");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(realPath, fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //为文件设置编码格式
        String encode = null;
        try {
            encode = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置下载方式以及下载后的文件名
        response.setHeader("content-disposition","attachment;fileName="+encode);
        //通过输出流给客户端打印数据
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //文件传输  读取过程中给客户端响应数据
        byte[] bytes = new byte[2048];
        while (true){
            //返回值代表读取的个数  如果达到文件末尾  返回-1
            int read = 0;
            try {
                read = fileInputStream.read(bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (read == -1)break;
            //向外响应
            try {
                outputStream.write(bytes,0,read);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //关闭资源
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
