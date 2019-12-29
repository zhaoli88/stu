package com.cy.sys.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.exception.ServiceException;
import com.cy.sys.dao.RegistDao;
import com.cy.sys.entity.Regist;
import com.cy.sys.service.RegistService;
/*
 * 报名注册数据传入数据库的操作
 */

@Service
public class RegistServiceImpl implements RegistService{
	@Autowired
	private RegistDao registDao;

	@Override
	public int saveRegistObject(Regist entity) {
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
			int rows;
			//2.保存数据
			try{
			rows = registDao.insertObject(entity);
			}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("保存失败");
			}
			//3.返回数据
			return rows;
	}

}
