package com.cy.pj.sys.entity;
import java.io.Serializable;
import lombok.Data;
/**
 * 封装培训师的信息
 * @author 44734
 *
 */
@Data
public class SysCoach implements Serializable{
	
	private static final long serialVersionUID = -6720876364022396298L;

	private Integer id;
	private String cname;
	private Integer age;
	private Integer tage;
	private Integer jid;
	private String location;
	private String site;
	private String url;
	private String comment;
	
}
