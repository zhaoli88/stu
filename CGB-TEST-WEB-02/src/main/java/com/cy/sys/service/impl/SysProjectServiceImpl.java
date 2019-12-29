package com.cy.sys.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.common.exception.ServiceException;
import com.cy.common.vo.PageObject;
import com.cy.sys.dao.SysProjectDao;
import com.cy.sys.dao.SysUserProjectDao;
import com.cy.sys.entity.SysProject;
import com.cy.sys.service.SysProjectService;
@Service
public class SysProjectServiceImpl implements SysProjectService{

	@Autowired
	private SysProjectDao sysProjectDao;
	@Autowired
	private SysUserProjectDao sysUserProjectDao;
	
	@Override
	public PageObject<SysProject> findObjects(Integer categoryId, Integer pageCurrent) {
		System.out.println("查询所有的项目");
		if(pageCurrent == null || pageCurrent < 1){
			throw new ServiceException("页码值不正确");
		}
		// 定义每页显示值
		Integer pageSize = 8;
		// 查询出总记录数
		Integer rowCount = sysProjectDao.getRowCount(categoryId);
		if (rowCount == 0) {
			throw new ServiceException("没有相应的项目");
		}
		// 计算出总页数
		Integer pageCount = (rowCount-1) / pageSize + 1;
		// 计算开始行
		Integer startIndex = pageSize*(pageCurrent-1);
		List<SysProject> sysFilms = sysProjectDao.selectObjects(startIndex, pageSize, categoryId);
		// 封装参数到pageObject
		PageObject<SysProject> page = new PageObject<>();
		page.setPageCount(pageCount);
		page.setPageCurrent(pageCurrent);
		page.setPageSize(pageSize);
		page.setRecords(sysFilms);
		page.setRowCount(rowCount);
		// 返回参数
		return page;
	}

	@Override
	public List<SysProject> findProjectByName(String name) {
		System.out.println("根据项目查询数据库");
		if (StringUtils.isEmpty(name)) {
			throw new ServiceException("请输入项目名");
		}
		List<SysProject> SysFilms = sysProjectDao.findProjectByName(name);
		if (SysFilms == null || SysFilms.size()==0) {
			throw new ServiceException("没有相关项目");
		}
		return SysFilms;
	}

	@Override
	public int saveObjectByProjectName(Integer userId, String filmName) {
		System.out.println("根据用用户id存储收藏的项目的名称");
		if (filmName == null) {
			throw new ServiceException("项目名不存在");
		}
		int count = sysUserProjectDao.insertObjectByName(userId, filmName);
		return count;
	}

	@Override
	public List<SysProject> findObjectById(Integer userId) {
		System.out.println("根据用户id查询数据库");
		//1.校验
		if(userId ==null||userId < 1)
			throw new IllegalArgumentException("用户不存在");
		//2.查询项目列表
		List<String> filmNames = sysUserProjectDao.findObjectById(userId);
		if(filmNames == null)
			throw new ServiceException("没事收藏记录");
		//3.获取项目id
		List<SysProject> sysFilms = sysProjectDao.findObjectsByFilmNames(filmNames);
		return sysFilms;
	}

}
