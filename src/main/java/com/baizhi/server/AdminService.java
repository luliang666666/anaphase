package com.baizhi.server;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Role;

import java.util.List;

public interface AdminService {
    Admin queryByUsername(String username);

    public List<Role> queryRole(String adminId);

    public List<String> queryPermission(String roleId);
}
