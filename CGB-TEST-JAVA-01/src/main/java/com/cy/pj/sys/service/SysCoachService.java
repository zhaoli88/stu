package com.cy.pj.sys.service;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysCoach;

public interface SysCoachService {
	
	PageObject<SysCoach> findPageObjects(String cname,
			                             Integer pageCurrent);
	
	int deleteObject(Integer id);
	
	SysCoach findCoachById(Integer id);
	
	int insertCoach(SysCoach entity);
	
	int updateObject(SysCoach entity);

}
