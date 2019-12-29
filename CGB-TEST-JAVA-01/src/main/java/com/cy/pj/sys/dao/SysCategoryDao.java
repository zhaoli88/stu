package com.cy.pj.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.cy.pj.common.vo.Node;

/**
 * 这是查询树结构的模块(项目添加里的 类型选择)
 * @author 44734
 *
 */
@Mapper
public interface SysCategoryDao {

	List<Node> findCategorys();
	Node findObjectById(Integer id);
}
