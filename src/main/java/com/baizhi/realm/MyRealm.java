package com.baizhi.realm;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Role;
import com.baizhi.server.AdminService;
import com.baizhi.util.RealmUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;


public class MyRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得主身份
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //根据主身份查主体  根据主体查角色  根据角色查权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        /*
         *  1.根据主身份查用户
         *  2.根据用户查角色
         *  3.根据角色查权限
         */
        //根据工具类调用PermissionService
        AdminDao bean = RealmUtil.getBean(AdminDao.class);
        AdminService adminService = RealmUtil.getBean(AdminService.class);
        //1.根据主身份查用户
        Admin admin = bean.queryByUsername(primaryPrincipal);
        //2.根据用户查角色
        List<Role> roles = adminService.queryRole(admin.getId());
        //根据角色查权限
        HashSet<String> hashSet = new HashSet<>();
        for (Role role : roles) {
            simpleAuthorizationInfo.addRole(role.getName());
            List<String> strings = adminService.queryPermission(role.getId());
            for (String string : strings) {
                hashSet.add(string);
            }
        }
        simpleAuthorizationInfo.addStringPermissions(hashSet);
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据token 获得身份信息
        String principal = (String) authenticationToken.getPrincipal();
        //调用工具类
        AdminDao bean = RealmUtil.getBean(AdminDao.class);
        //连接数据库查询
        Admin admin = bean.queryByUsername(principal);
        AuthenticationInfo authenticationInfo = null;
        if (admin == null) {
            return null;
        } else {
            //this.getName()  当前类的类对象
            authenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), this.getName());
        }
        return authenticationInfo;
    }
}
