package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.BannerDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Banner;
import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = AnaphaseApplication.class)
@RunWith(SpringRunner.class)
public class PoiTest {
    @Test
    public void test(){
        //创建excle文件
        HSSFWorkbook sheets = new HSSFWorkbook();
        HSSFFont font = sheets.createFont();
        //加粗
        font.setBold(true);
        //红色
        font.setColor(Font.COLOR_RED);
        //字体
        font.setFontName("繁体");
        //创建工作簿
        HSSFSheet sheet = sheets.createSheet("表格");
        //创建行   0：代表第一行
        HSSFRow row = sheet.createRow(0);
        //创建单元格
        HSSFCell cell = row.createCell(0);
        //单元格设值
        cell.setCellValue("寻龙分金看缠山");
        //指定Excle输出的位置以及文件名
        try {
            sheets.write(new File("E:/test.xls"));
            //释放资源
            sheets.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private UserDao userDao;
    @Test
    public void test1(){
        List<User> query = userDao.query();
        for (User dao : query) {
            System.out.println(dao);
        }
    }
    @Test
    public void test2(){
        //创建Excle
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        //自定义日期类型
        short format = dataFormat.getFormat("yyyy-MM-dd hh:mm:ss");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)20);
        //设置字体
        font.setFontName("微软雅黑");
        //字体加粗
        font.setBold(true);
        //字体红色
        font.setColor(Font.COLOR_RED);
        //信息设置
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font);
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        //创建工作簿
        HSSFSheet sheet = workbook.createSheet("信息表");
        sheet.setColumnWidth(6,20*256);
        //创建行
        HSSFRow row = sheet.createRow(0);
        //自定义标题行
        String[] titles = {"id","姓名","密码","日期","电话","状态"};
        for (int i = 0; i < titles.length; i++){
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle);
        }
        //创建单元格
        List<User> list = userDao.query();
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getName());
            row1.createCell(2).setCellValue(list.get(i).getPassword());

            row1.createCell(4).setCellValue(list.get(i).getPhone());
            row1.createCell(5).setCellValue(list.get(i).getState());
            HSSFCell cell = row1.createCell(3);
            cell.setCellValue(list.get(i).getBirthday());
            cell.setCellStyle(cellStyle1);
        }
        //指定Excle输出的位置以及文件名
        try {
            workbook.write(new File("E:/test1.xls"));
            //释放资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test3(){
        List<User> list = userDao.query();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),
                User .class, list);
        try {
            workbook.write(new FileOutputStream("E:/test2.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private BannerDao bannerDao;
    @Test
    public void test6(){
        List<Banner> list = bannerDao.query();
        for (Banner banner : list) {
            banner.setImg("F:\\框架阶段\\IDEACode\\anaphase\\src\\main\\webapp\\img\\"+banner.getImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机","学生"),
                Banner .class, list);
        try {
            //存放路径
            workbook.write(new FileOutputStream("E:/test3.xls"));
            //释放资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
