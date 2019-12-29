package com.cy.sys.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.exception.ServiceException;
import com.cy.sys.dao.SysBaoMingDao;
import com.cy.sys.entity.SysBaoMing;
import com.cy.sys.service.SysBaoMingService;

@Service
public class SysBaoMingServiceImpl implements SysBaoMingService{

	@Autowired
	private SysBaoMingDao sysBaoMingDao;
	
	
	//电话和姓名必须输入
	@Override
	public int insertObject(SysBaoMing entity) {
		if (entity.getSname() == null) {
			throw new ServiceException("请输入名字");
		}
		
		if(entity.getTel()==null)
			throw new ServiceException("请输入电话");
		int count = sysBaoMingDao.insertObject(entity);
		return count;
	}

}
