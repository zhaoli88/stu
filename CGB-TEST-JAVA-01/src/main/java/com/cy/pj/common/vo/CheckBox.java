package com.cy.pj.common.vo;
import java.io.Serializable;
import lombok.Data;
/**
 * 基于此类对象封装 角色 id,name信息
 * @author 44734
 *
 */
@Data
public class CheckBox implements Serializable{

	private static final long serialVersionUID = 4726362356109662723L;

	private Integer id;
	private String name;
}
