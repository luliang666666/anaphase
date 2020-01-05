package com.baizhi.server;

import com.baizhi.entity.Admin;

public interface AdminService {
    Admin queryByUsername(String username);
}
