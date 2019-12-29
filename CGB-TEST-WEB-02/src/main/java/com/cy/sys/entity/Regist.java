package com.cy.sys.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 封装注册信息
 * @author 44734
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Regist {
	
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
