package com.cy.pj.common.vo;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来封装分页信息的对象
 * @author 44734
 *
 * @param <T>代表泛型类
 */
@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable{


	/**
	 * 页面信息封装
	 */
	private static final long serialVersionUID = 4382188489836324755L;
	//当前页码值
	private Integer pageCurrent=1;
    //当前页面大小
	private Integer pageSize=3;
    //查询获得的总行数
	private Integer rowCount=0;
    //总页数(需计算)
	private Integer pageCount=0;
    //当前页记录,T代表泛型
	private List<T> records;
	
	public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
		super();
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.records = records;
		//通过公式计算得到页面总数
		this.pageCount=(rowCount-1)/pageSize+1;
	}
	
}
