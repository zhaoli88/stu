package com.cy.pj.sys.entity;
import lombok.Data;

@Data
public class SysProject {
	
	/** 该文件对象的唯一表示id*/
	private Integer id;
	/** 文件名称*/
	private String name;
	/** 文件标签*/
	private String lable;
	/** 上映时间*/
	private String onTime;
	/** 评分*/
	private Double grade;
	/** 介绍*/
	private String introduce;
	/** 文件类型*/
	private Integer categoryId;
	/** 所属地区*/
	private String area;
	/** 文件存储路径*/
	private String route;
	private Integer vip;

}
