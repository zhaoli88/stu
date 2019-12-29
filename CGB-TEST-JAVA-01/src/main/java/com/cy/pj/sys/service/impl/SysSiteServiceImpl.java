package com.cy.pj.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysSiteDao;
import com.cy.pj.sys.entity.SysSite;
import com.cy.pj.sys.service.SysSiteService;

@Service
public class SysSiteServiceImpl implements SysSiteService{

	@Autowired
	private SysSiteDao sysSiteDao;
	@Autowired
	private PageProperties pageProperties;
	
	
	
	@Override
	public PageObject<SysSite> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = sysSiteDao.getRowCount(name);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		int pageSize=pageProperties.getPageSize();
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysSite> records = sysSiteDao.findPageObjects(name, startIndex, pageSize);
		//if(records==null || records.size()==0)throw new ServiceException("没有该用户");
		PageObject<SysSite> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		
		return pageObject;
	}

	@Override
	public int saveObject(SysSite entity) {
		        //1.验证参数有效性
				if(entity==null)
					throw new IllegalArgumentException("保存对象不能为空");
				//2.保存数据
				int rows=sysSiteDao.insertObject(entity);
				if(rows==0)
					throw new ServiceException("对象已丢失");
				//3.返回结果
				return rows;
	}

	@Override
	public int deleteObject(Integer id) {
		       //1.验证参数有效性
				if(id==null||id<1)
				throw new IllegalArgumentException("参数无效");
				//3.基于id删除菜单表中记录
				int rows=sysSiteDao.deleteObject(id);
				//5.返回删除结果
				return rows;
	}

}
