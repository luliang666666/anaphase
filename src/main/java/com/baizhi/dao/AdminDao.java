package com.baizhi.dao;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    List<Admin> queryAll();
    Admin queryByUsername(String username);

    public List<Role> queryRole(@Param("adminId") String adminId);

    public List<String> queryPermission(@Param("roleId") String roleId);
}
