package com.edu.archives;

// MyBatis Mapper 扫描注解。
import org.mybatis.spring.annotation.MapperScan;
// Spring Boot 启动工具。
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Boot 主启动类。
// 调用关系：JVM 运行 main 方法 -> SpringApplication.run -> 初始化整个应用上下文。
@SpringBootApplication
// 扫描 Mapper 接口包，交给 MyBatis 生成代理实现。
@MapperScan("com.edu.archives.mapper")
public class ArchivesManagementApplication {
	// 应用入口方法。
    public static void main(String[] args) {
		// 启动 Spring Boot。
        SpringApplication.run(ArchivesManagementApplication.class, args);
    }
}