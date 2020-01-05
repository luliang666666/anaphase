package com.baizhi.controller;

import com.baizhi.util.ValidateImageCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/code")
public class CodeController {
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session){
        //回执验证码中的字符
        String code = ValidateImageCodeUtils.getSecurityCode();
        //通过生成的字符回执图片
        BufferedImage image = ValidateImageCodeUtils.createImage(code);
        //通过session作用域存储数据
        session.setAttribute("code",code);
        //通过图片的输出流  将图片写到页面
        //参数一：图片  参数二：格式   参数三：输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image,"png",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
