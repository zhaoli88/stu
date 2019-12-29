package com.cy.pj.common.config;
import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 配置shiro单元
 * @author 44734
 *
 */
@Configuration
public class SpringShiroConfig {
	
	/**
	 * 配置advisor对象,
	 * shiro框架底层会通过此对象的matchs方法返回值(类似切入点)决定是否创建代理对象,
	 * 进行权限控制。
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor 
	newAuthorizationAttributeSourceAdvisor(
		    		    SecurityManager securityManager) {
			        AuthorizationAttributeSourceAdvisor advisor=
					new AuthorizationAttributeSourceAdvisor();
	advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	
	/**
	 * 将需要的配置加入到SecurityManager
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(Realm realm) {
		DefaultWebSecurityManager sManager = 
				new DefaultWebSecurityManager();
		 sManager.setRealm(realm);
		return sManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(
			SecurityManager securityManager) {
		ShiroFilterFactoryBean sBean = 
				new ShiroFilterFactoryBean();
		sBean.setSecurityManager(securityManager);
		//定义login的URL
		sBean.setLoginUrl("/doLoginUI");
		//定义LinkedHasMap ,将请求过滤进行分类操作
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		//静态资源允许匿名访问:"anon"
		 map.put("/bower_components/**","anon");
		 map.put("/build/**","anon");
		 map.put("/dist/**","anon");
		 map.put("/plugins/**","anon");
		 map.put("/login01/**","anon");
		 map.put("/user/doLogin","anon");
		 map.put("/doLogout","logout");
		 //除了匿名访问的资源,其它都要认证("authc")后访问
		 map.put("/**","authc");
		 sBean.setFilterChainDefinitionMap(map);		
		return sBean;
	}
	
	
}
