package com.cy.pj.sys.service;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysSite;

public interface SysSiteService {
	
	/**
	 * 执行菜单的删除操作
	 * 1)有子菜单则不允许删除
	 * 2)删除当前菜单信息
	 * 3)删除当前菜单与角色的关系数据
	 * @param id 菜单id
	 * @return 删除的行数
	 */
	int deleteObject(Integer id);
	
	/**
	 * 保存菜单信息
	 * @param entity
	 * @return
	 */
	int saveObject(SysSite entity);

	/**
	 * 保存菜单信息
	 * @param entity
	 * @return
	 */
	
	PageObject<SysSite> findPageObjects(String name,Integer pageCurrent);
	
	
	
}
