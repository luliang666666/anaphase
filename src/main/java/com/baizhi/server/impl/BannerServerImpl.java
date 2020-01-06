package com.baizhi.server.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.server.BannerService;
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
public class BannerServerImpl implements BannerService {
    //依赖于BannerDao
    @Autowired
    private BannerDao bannerDao;
    @Override
    //@AddCache
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer start = (page-1)*rows;
        List<Banner> list = bannerDao.queryPage(start, rows);
        /*
         *  jqgrid 返回  page  rows  records total
         * */
        //records  总条数
        Integer records = bannerDao.count();
        //total 总页数
        Integer total = records%rows==0 ? records/rows:records/rows+1;
        map.put("page",page);
        map.put("rows",list);
        map.put("records",records);
        map.put("total",total);
        return map;
    }
    @Override
    //@ClearCache
    public String insert(Banner banner) {
        String s = UUID.randomUUID().toString();
        banner.setId(s);
        banner.setOther("");
        bannerDao.insert(banner);
        return s;
    }

    //@ClearCache
    public void delete(String[] id) {
        bannerDao.delete(id);
    }

    //@ClearCache
    public void update(Banner banner) {
        bannerDao.update(banner);
    }

    //@AddCache
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> query() {

        return bannerDao.query();
    }
}
