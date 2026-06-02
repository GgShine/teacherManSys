package com.edu.archives.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// JWT 配置绑定类。
// 调用关系：Spring 启动时从 application.yml 的 jwt 前缀读取值并注入本对象。
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
	// JWT 密钥。
	private String secret;
	// 过期时间（秒或业务约定单位）。
	private Long expire;

	// 获取密钥。
	public String getSecret() {
		return secret;
	}

	// 设置密钥（配置绑定时调用）。
	public void setSecret(String secret) {
		this.secret = secret;
	}

	// 获取过期时间。
	public Long getExpire() {
		return expire;
	}

	// 设置过期时间（配置绑定时调用）。
	public void setExpire(Long expire) {
		this.expire = expire;
	}
}
