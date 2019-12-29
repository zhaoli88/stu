package com.cy.pj.sys.service;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;

public interface SysLogService {
	
	int insertObject(SysLog log);
	//删除操作
	int deleteLogsById(Integer...ids);

	/** 依据用户名称查询当前页用户日志数据*/
	PageObject<SysLog> findPageObjects(
			String name, Integer pageCurrent);
	
}
