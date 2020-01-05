package com.baizhi.server.impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.server.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    //依赖于ChapterDao
    @Autowired
    private ChapterDao chapterDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByPage(Integer page, Integer rows,String id) {
        HashMap<String, Object> map = new HashMap<>();
        Integer start = (page-1)*rows;
        List<Chapter> list = chapterDao.queryByPage(start, rows,id);
        /*
         *  jqgrid 返回  page  rows  records total
         * */
        //records  总条数
        Integer records = chapterDao.count();
        //total 总页数
        Integer total = records%rows==0 ? records/rows:records/rows+1;
        map.put("page",page);
        map.put("rows",list);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    public String insert(Chapter chapter) {
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        String id = chapter.getAlbum_id();
        chapter.setAlbum_id(id);
        chapter.setOther("");
        chapterDao.insert(chapter);
        return s;

    }

    @Override
    public void delete(String id) {
        chapterDao.delete(id);
    }

    @Override
    public void update(Chapter chapter) {
        chapterDao.update(chapter);
    }

    @Override
    public List<Chapter> queryByAlbum_id(String album_id) {
        List<Chapter> chapters = chapterDao.queryById(album_id);
        return chapters;
    }
}
