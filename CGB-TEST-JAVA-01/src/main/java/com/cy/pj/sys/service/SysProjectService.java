package com.cy.pj.sys.service;
import java.util.Map;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysProject;

public interface SysProjectService {
	
	int insertProject(SysProject entity);
	
	SysProject findProjectById(Integer id);
	
	int updateObject(SysProject entity);
	
	int deleteObjects(Integer...ids);
	
	PageObject<Map<String,Object>> findPageObjects(String name,Integer pageCurrent);

}
