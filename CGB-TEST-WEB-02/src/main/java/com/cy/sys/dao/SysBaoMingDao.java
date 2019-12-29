package com.cy.sys.dao;
import org.apache.ibatis.annotations.Mapper;
import com.cy.sys.entity.SysBaoMing;
@Mapper
public interface SysBaoMingDao {
	//导入数据
	int insertObject(SysBaoMing entity);

}
