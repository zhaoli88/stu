package com.cy.pj.sys.entity;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class SysStudents implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;
	private String sname;
	private String gender;
	private Integer age;
	private String nember;
	private String home;
	private String tel;
	private String email;
	private String place;
	private String coachs;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}







}
