package com.cy.pj.common.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * 拓展内容,读取yml文件的内容,注意其注解
 * @author 44734
 *
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "db.page")
public class PageProperties {

	private int pageSize;
}
