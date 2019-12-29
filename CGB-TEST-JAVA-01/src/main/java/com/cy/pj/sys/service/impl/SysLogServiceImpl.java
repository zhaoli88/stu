package com.cy.pj.sys.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService{

	@Autowired
	private SysLogDao sysLogDao;
	@Autowired
	private PageProperties pageProperties;
	
	@Override
	public PageObject<SysLog> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
			
		int pageSize=pageProperties.getPageSize();
			int startIndex=(pageCurrent-1)*pageSize;
			List<SysLog> records = 
					sysLogDao.findPageObjects(name, startIndex, pageSize);
			int rowCount = sysLogDao.getRowCount(name);
			if(records == null || records.size()==0)
				throw new ServiceException("系统没有查询到对应记录");
			PageObject<SysLog> pageObject = new PageObject<SysLog>();
			pageObject.setPageCurrent(pageCurrent);
			pageObject.setPageSize(pageSize);
		    pageObject.setRowCount(rowCount);
			pageObject.setRecords(records);
			return pageObject;
	}

	@Override
	public int deleteLogsById(Integer... ids) {
		if(ids==null||ids.length<0)
			throw new ServiceException("请选中要删除的数据");
		int rows = sysLogDao.deleteLogsById(ids);
		if(rows == 0){
			throw new ServiceException("数据失败或数据不存在，请刷新后重试");
		}
		return rows;
	}

	@Override
	public int insertObject(SysLog log) {
		if(log == null)
			throw new IllegalArgumentException("日志记录异常!请尽快联系开发人员进行修改");
		int rows = sysLogDao.insertObject(log);
		return rows;
	}

}
