package com.cy.sys.entity;

import java.io.Serializable;
import lombok.Data;
@Data
public class SysBaoMing implements Serializable{
	
	private static final long serialVersionUID = -992080435333974004L;

	private String type;
	private String sname;
	private String gender;
	private Integer age;
	private Integer nember;
	private String home;
	private Integer tel;
	private String email;
	private String place;
	
}
