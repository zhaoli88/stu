package com.cy.pj.common.util;
import org.apache.shiro.SecurityUtils;
import com.cy.pj.sys.entity.SysUser;
/**
 * shiro的工具类
 * @author 44734
 *
 */
public class ShiroUtils {
	
   public static String getUsername() {
	   return getUser().getUsername();
   }
   
   /**获取登录用户*/
   public static SysUser getUser() {
	   return (SysUser)SecurityUtils.getSubject().getPrincipal();
   }
   
   
}
