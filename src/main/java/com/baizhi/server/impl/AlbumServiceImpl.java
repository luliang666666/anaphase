package com.baizhi.server.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.server.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    //依赖于AlbumDao
    @Autowired
    private AlbumDao albumDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer start = (page-1)*rows;
        List<Album> list = albumDao.queryPage(start, rows);
        /*
         *  jqgrid 返回  page  rows  records total
         * */
        //records  总条数
        Integer records = albumDao.count();
        //total 总页数
        Integer total = records%rows==0 ? records/rows:records/rows+1;
        map.put("page",page);
        map.put("rows",list);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    public List<Album> queryAll() {
        List<Album> albums = albumDao.queryAll();
        return albums;
    }

    @Override
    public Album queryById(String id) {
        return albumDao.selectById(id);
    }
}
