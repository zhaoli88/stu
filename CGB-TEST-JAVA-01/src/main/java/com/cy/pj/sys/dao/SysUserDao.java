package com.cy.pj.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao extends BaseDao{
	
	//shiro操作相关
	SysUser findUserByUserName(String username);
	
	//修改密码操作,根据id修改密码,加入盐值
	int updatePassword(@Param("password")String password,
			           @Param("salt")String salt,
			           @Param("id")Integer id);
	
	//数据更新操作
	int updateObject(SysUser entity);
	
	
	  //编辑回显 
	SysUserDeptVo findObjectById(Integer id);
	 
	
	//添加操作
	int insertObject(SysUser entity);
	
	
	//设置权限,关键是valiId=1或=0 modifiedUser修改用户状态
	int validById(@Param("id") Integer id,
			      @Param("valid") Integer valid,
			      @Param("modifiedUser") String modifiedUser);
	
	//根据username用户名, startIndex起始页, pageSize页面大小 查找
	
	  List<SysUserDeptVo> findPageObjects(@Param("username") String username,
	                                      @Param("startIndex") Integer startIndex,
	                                      @Param("pageSize") Integer pageSize);
	 
	//查询操作
	int getRowCount(@Param("username") String username);

}
