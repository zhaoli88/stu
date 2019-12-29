package com.cy.sys.service;
import java.util.List;
import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysProject;

public interface SysProjectService {

	/**
	 * 查询所有的项目，根据页码值，页码值是必须的值，如果有分类的话，根据分类查询
	 * @param category
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysProject> findObjects(Integer categoryId, Integer pageCurrent);
	
	List<SysProject> findProjectByName(String name);
	
	int saveObjectByProjectName(Integer userId, String filmName);
	
	List<SysProject> findObjectById(Integer userId);
	
}
