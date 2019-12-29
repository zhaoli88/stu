package com.cy.sys.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
@Data
public class SysProject implements Serializable{

	
	private static final long serialVersionUID = 6359406070471573009L;

	/** 该文件对象的唯一表示id*/
	private Integer id;
	/** 文件名称*/
	private String name;
	/** 文件标签*/
	private String lable;
	/** 上映时间*/
	private Date onTime;
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
