package com.baizhi.server.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Role;
import com.baizhi.server.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    //依赖于AdminDao
    @Autowired
    private AdminDao adminDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin queryByUsername(String username) {
        return adminDao.queryByUsername(username);
    }

    @Override
    public List<Role> queryRole(String adminId) {
        List<Role> roles = adminDao.queryRole(adminId);
        return roles;
    }

    @Override
    public List<String> queryPermission(String roleId) {
        List<String> strings = adminDao.queryPermission(roleId);
        return strings;
    }
}
