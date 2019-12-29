package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * 部门PO对象
 */
@Data
public class SysDept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -214899301719182809L;
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer sort;
	private String note;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
