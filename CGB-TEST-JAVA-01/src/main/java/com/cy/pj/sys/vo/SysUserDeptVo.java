package com.cy.pj.sys.vo;
import java.io.Serializable;
import java.util.Date;
import com.cy.pj.sys.entity.SysDept;

import lombok.Data;


/**
 * 用于接收封装User对象
 * @author 44734
 *通过此对象除了可以封装从数据库查询的数据，还可以封装客户端请求数据，实现层与层之间数据的传递。
 *同时封装有部门数据
 */

@Data
public class SysUserDeptVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138534420870847335L;
	private Integer id;
	private String username;
	private String password;//md5
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	//private Integer deptId;
	private SysDept sysDept; 
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;

}
