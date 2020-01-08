package com.baizhi.realm;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroFilter {
    //注意事项 方法名必须为shiroFilterFactoryBean
    //交由工厂管理
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        HashMap<String, String> map = new HashMap<>();
        //释放
        map.put("/login/assets/**", "anon");
        map.put("/login/login.jsp", "anon");
        map.put("/admin/login", "anon");
        map.put("/jquery/**", "anon");
        map.put("/code/getCode", "anon");
        map.put("/boot/**", "anon");
        map.put("/jqgrid/**", "anon");
        map.put("/kindeditor/**", "anon");
        //所有资源都是认证资源
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面的路径（shiro默认跳转的页面login.jsp）
        shiroFilterFactoryBean.setLoginUrl("/login/login.jsp");
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setCacheManager(memoryConstrainedCacheManager());
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    //缓存
    @Bean
    public MemoryConstrainedCacheManager memoryConstrainedCacheManager() {
        MemoryConstrainedCacheManager memoryConstrainedCacheManager = new MemoryConstrainedCacheManager();
        return memoryConstrainedCacheManager;
    }
}
