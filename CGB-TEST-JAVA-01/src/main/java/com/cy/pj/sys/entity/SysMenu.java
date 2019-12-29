package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class SysMenu implements Serializable{
	/**添加菜单是添加的
	 * 定义持久化对象，封装客户端请求数据，并将数据传递到数据层进行持久化。
	 */
	private static final long serialVersionUID = 6957715733386177156L;
	

	private Integer id;
	/**菜单名称*/
	private String name;
	/**菜单url: log/doFindPageObjects*/
	private String url;
	/**菜单类型(两种:按钮,普通菜单)*/
	private Integer type=1;
	/**排序(序号)*/
	private Integer sort;
	/**备注*/
	private String note;
	/**上级菜单id*/
	private Integer parentId;
	/**菜单对应的权限标识(sys:log:delete)*/
	private String permission;
	/**创建用户*/
	private String createdUser;
	/**修改用户*/
	private String modifiedUser;
	private Date createdTime;
	private Date modifiedTime;
	
}