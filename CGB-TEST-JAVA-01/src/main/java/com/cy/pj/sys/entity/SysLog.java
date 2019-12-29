package com.cy.pj.sys.entity;
import java.util.Date;
import lombok.Data;
@Data
public class SysLog {
	private Integer id;
	//用户名
	private String name;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createdTime;
}
