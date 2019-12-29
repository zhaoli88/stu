package com.cy.pj.sys.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysCoachDao;
import com.cy.pj.sys.entity.SysCoach;
import com.cy.pj.sys.service.SysCoachService;

@Service
public class SysCoachServiceImpl implements SysCoachService{

	@Autowired
	private SysCoachDao sysCoachDao;
	
	@Autowired
	private PageProperties pageProperties;
	
	/**
	 * 页面显示数据
	 */
	@Override
	public PageObject<SysCoach> findPageObjects(String cname, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = sysCoachDao.getRowCount(cname);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		int pageSize=pageProperties.getPageSize();
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysCoach> records = sysCoachDao.findPageObjects(cname, startIndex, pageSize);
		if(records==null || records.size()==0)
			throw new ServiceException("没有该用户");
		PageObject<SysCoach> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		
		return pageObject;
	}

	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1)
			throw new ServiceException("id的值不正确,id="+id);
		int row = sysCoachDao.deleteObject(id);
		if(row==0)
			throw new ServiceException("记录不存在");
		return row;
	}

	@Override
	public SysCoach findCoachById(Integer id) {
		if(id==null)
			throw new ServiceException("未找到");
		return sysCoachDao.findCoachById(id);
	}

	@Override
	public int insertCoach(SysCoach entity) {
		if(entity==null)
			throw new ServiceException("请输入有效数据");
		if(StringUtils.isEmpty(entity.getCname()))
			throw new ServiceException("请输入名字");
		sysCoachDao.insertCoach(entity);
		return sysCoachDao.insertCoach(entity);
	}

	@Override
	public int updateObject(SysCoach entity) {
		if(entity==null)
			throw new ServiceException("未选中");
		return sysCoachDao.updateObject(entity);
	}
	
	
	

}
