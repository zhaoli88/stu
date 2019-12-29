package com.cy.pj.sys.service;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {
	
	
	//公用模板
    int isExist(String columnName,String columnValue);
	
	int updatePassword(String password,
			           String newPassword,
			           String cfgPassword);
	
	int updateObject(SysUser entity,Integer[] roleIds);
	
	Map<String,Object> findObejctById(Integer userId);
	
	int saveObject(SysUser entity, Integer[] roleIds);
	
	int validById(Integer id,Integer valid,String modifiedUser);
	
	
	PageObject<SysUserDeptVo> findPageObjects(String username,
			                                  Integer pageCurrent);
	 
}
