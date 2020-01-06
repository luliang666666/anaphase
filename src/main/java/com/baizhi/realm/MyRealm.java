package com.baizhi.realm;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.util.RealmUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得主身份
        //String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //根据主身份查主体  根据主体查角色  根据角色查权限

        return null;
    }

    //认证

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据token 获得身份信息
        String principal = (String) authenticationToken.getPrincipal();
        //连接数据库查询
        //调用静态方法
        AdminDao bean = RealmUtil.getBean(AdminDao.class);

        Admin admin = bean.queryByUsername(principal);
        AuthenticationInfo authenticationInfo = null;
        if (principal.equals(admin.getUsername())) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), this.getName());

        }
        return authenticationInfo;
    }
}
