package com.cy.pj.sys.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysSite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6730281877756033216L;
	private int id;
	private String name;
	private String city;
	private String site;
	private String tel;
	//备注
    private String comment;

}
