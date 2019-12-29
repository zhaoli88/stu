package com.cy.pj.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.pj.common.annotation.LogAnnotation;
import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysStudentsDao;
import com.cy.pj.sys.entity.SysStudents;
import com.cy.pj.sys.service.SysStudentsService;

@Service
public class SysStudentsServiceImpl implements SysStudentsService{

	@Autowired
	private SysStudentsDao sysStudentsDao;
	@Autowired
	private PageProperties pageProperties;
	
	@LogAnnotation
	@Override
	public PageObject<SysStudents> findPageObject(String sname, Integer pageCurrent) {
		//1.数据合法性验证
				if(pageCurrent==null||pageCurrent<=0)
					throw new ServiceException("参数不合法");
				
				//2.依据条件获取总记录数
				int rowCount=sysStudentsDao.getRowCount(sname);
				if(rowCount==0)
					throw new ServiceException("记录不存在");
				//3.计算startIndex的值
				int pageSize=pageProperties.getPageSize();
				int startIndex=(pageCurrent-1)*pageSize;
				//4.依据条件获取当前页数据
				List<SysStudents> records=
						sysStudentsDao.findPageObject(
								sname, startIndex, pageSize);
				System.out.println(records.toString());
				//5.封装数据
				PageObject<SysStudents> pageObject=new PageObject<>();
				pageObject.setPageCurrent(pageCurrent);
				pageObject.setRowCount(rowCount);
				pageObject.setPageSize(pageSize);
				pageObject.setRecords(records);
				//pageObject.setPageCount((rowCount-1)/pageSize+1);
				System.out.println(pageObject);
				return pageObject;
	}

	@LogAnnotation
	@Override
	public int insertStudent(SysStudents sysStudents) {
		if(sysStudents==null)
			throw new ServiceException("请输入有效数据");
		if(StringUtils.isEmpty(sysStudents.getSname()))
			throw new ServiceException("请输入用户名");
		
		int rows = sysStudentsDao.insertStudent(sysStudents);
		if(rows == 0)
			throw new ServiceException("添加失败");
		return rows;
	}

	@Override
	public SysStudents findStudentById(Integer id) {
		return sysStudentsDao.findStudentById(id);
	}

	@LogAnnotation
	@Override
	public int deleteObjects(Integer... ids) {
		if(ids==null||ids.length<1)
			throw new ServiceException("请选中要删除的数据");
		int rows=sysStudentsDao.deleteObjects(ids);
		if(rows<1)throw new ServiceException("删除失败");	
		return rows;
	}

	@LogAnnotation
	@Override
	public int updateObject(SysStudents entity) {
		if(entity==null)
			throw new ServiceException("请输入有效数据");
		if(StringUtils.isEmpty(entity.getSname()))
			throw new ServiceException("请输入姓名");
		if(StringUtils.isEmpty(entity.getType()))
			throw new ServiceException("请输入类型");
		
		if(StringUtils.isEmpty(entity.getGender()))
			throw new ServiceException("请输入性别");
		if(StringUtils.isEmpty(entity.getHome()))
			throw new ServiceException("请输入地址");
		if(StringUtils.isEmpty(entity.getEmail()))
			throw new ServiceException("请输入邮箱");
		/*
		 * if(StringUtils.isEmpty(entity.getId())) throw new ServiceException("请输入ID");
		 */
		if(StringUtils.isEmpty(entity.getTel()))
			throw new ServiceException("请输入电话");
		if(StringUtils.isEmpty(entity.getPlace()))
			throw new ServiceException("请输入场地");	
		int rows = sysStudentsDao.updateObject(entity);
		if(rows == 0)
			throw new ServiceException("修改失败");
		return rows;
	}

	
	
}
