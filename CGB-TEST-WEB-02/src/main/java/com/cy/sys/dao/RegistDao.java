package com.cy.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cy.sys.entity.Regist;

//报名注册
@Mapper
public interface RegistDao {
	int insertObject(Regist regist);

}
