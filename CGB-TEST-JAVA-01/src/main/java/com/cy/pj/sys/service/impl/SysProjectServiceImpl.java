package com.cy.pj.sys.service.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import com.cy.pj.common.annotation.LogAnnotation;
import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysProjectDao;
import com.cy.pj.sys.entity.SysProject;
import com.cy.pj.sys.service.SysProjectService;
@Service
public class SysProjectServiceImpl implements SysProjectService{
 
	@Autowired
	private SysProjectDao sysProjectDao;
	@Autowired
	private PageProperties pageProperties;
	
	@LogAnnotation
	@Override
	public PageObject<Map<String, Object>> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = sysProjectDao.getRowCount(name);
		int pageSize=pageProperties.getPageSize();
		//int pageSize=8;
		int startIndex=(pageCurrent-1)*pageSize;
		List<Map<String,Object>> records = 
				sysProjectDao.findPageObjects(name,startIndex, pageSize);
		System.out.println(records);
		if(records==null || records.size()==0)
			throw new ServiceException("系统没有查询到对应记录");
		PageObject<Map<String,Object>> pageObject = 
				new PageObject<Map<String,Object>>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		return pageObject;
	}

	@LogAnnotation
	@Override
	public int deleteObjects(Integer... ids) {
		if(ids==null|ids.length<1)
			throw new ServiceException("请选中要删除的数据");
		int rows = sysProjectDao.delteObjects(ids);
		if(rows <1)
			throw new ServiceException("删除失败");
		return rows;
	}

	@LogAnnotation
	@Override
	public int updateObject(SysProject entity) {
		if(entity==null)
			throw new ServiceException("请输入有效数据");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("请输入项目名");
		if(StringUtils.isEmpty(entity.getArea()))
			throw new ServiceException("请输入项目来源地区");
		
		if(StringUtils.isEmpty(entity.getRoute()))
			throw new ServiceException("请输入存放路径地址");
		System.out.println("impl:"+entity);
		int rows = sysProjectDao.updateObject(entity);
		if(rows == 0)
			throw new ServiceException("修改失败");
		return 0;
	}

	@Override
	public SysProject findProjectById(Integer id) {
		return sysProjectDao.findProjectById(id);
	}

	@LogAnnotation
	@Override
	public int insertProject(SysProject entity) {
		if(entity==null)
			throw new ServiceException("请输入有效数据");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("请输入项目名");
		if(StringUtils.isEmpty(entity.getArea()))
			throw new ServiceException("请输入项目来源地区");
		if(entity.getCategoryId()==null)
			throw new ServiceException("请选择项目类型");
		if(StringUtils.isEmpty(entity.getRoute()))
			throw new ServiceException("请输入存放路径地址");
		int rows = sysProjectDao.insertProject(entity);
		if(rows == 0)
			throw new ServiceException("添加失败");
		return rows;
	}

}
