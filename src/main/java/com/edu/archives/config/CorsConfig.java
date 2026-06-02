package com.edu.archives.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 全局跨域配置。
// 调用关系：Spring 启动时自动加载本配置类并应用到 MVC 层。
@Configuration
public class CorsConfig implements WebMvcConfigurer {

	// 注册跨域策略。
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 对全部路径开放跨域访问。
		registry.addMapping("/**")
				// 允许任意来源（生产环境建议收敛域名）。
				.allowedOriginPatterns("*")
				// 允许的 HTTP 方法。
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				// 允许任意请求头。
				.allowedHeaders("*")
				// 允许携带 Cookie/凭证。
				.allowCredentials(true)
				// 预检请求缓存秒数。
				.maxAge(3600);
	}
}
