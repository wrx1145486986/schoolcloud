//package com.wrx.schoolcould.config;
//
//import com.wrx.schoolcould.pojo.User;
//import com.wrx.schoolcould.service.UserService;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//
//@Configuration
//public class ShiroConfig {
//
//    /*
//     * 创建ShiroFilterFactoryBean对象
//     * */
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//        /*
//         * 添加 shiro 内置过滤器
//         *   常用过滤器
//         *       anon：无需认证就可以方位
//         *       authc：必须认证才能访问
//         *       user：必须使用了 rememberMe的功能才可以访问
//         *       perms：该资源必须授予资源权限才能访问
//         *       role：该资源必须授予角色权限才能访问
//         * */
//        HashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//
//
//        // 设置路径拦截链
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//        return shiroFilterFactoryBean;
//    }
//
//    /*
//     * 创建DefaultWebSecurityManager对象
//     * */
//    @Bean
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        defaultWebSecurityManager.setRealm(userRealm);
//        return defaultWebSecurityManager;
//    }
//
//    /*
//     * 创建 UserRealm 对象，交由spring容器管理
//     * */
//    @Bean
//    public UserRealm getUserRealm() {
//        return new UserRealm();
//    }
//
//}
//
//
///*
// *
// * 自定义realm类
// */
//class UserRealm extends AuthorizingRealm {
//
//    @Autowired
//    UserService userService;
//
//    /*
//     * 授权
//     * */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//
//
//        return null;
//    }
//
//    /*
//     * 认证
//     * */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
//            throws AuthenticationException {
//
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
//
//        String username = usernamePasswordToken.getUsername();
//        if (username != null && username != ""){
////            查询数据库
//            User user = null;
//
//            if(user == null){
////                查询不到用户数据
//                return null;
//            }else {
////                查询到该用户
////                使用盐值加密 防止出现密码相同时 密码加密的字串相同的情况
//                ByteSource bytes = ByteSource.Util.bytes(user.getName());
//                return new SimpleAuthenticationInfo(username ,user.getPassword(), bytes, this.getName());
//            }
//        }else {
//            return null;
//        }
//    }
//}