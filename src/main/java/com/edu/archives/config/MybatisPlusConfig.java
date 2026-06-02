
package com.edu.archives.config;

// 数据库类型枚举。
import com.baomidou.mybatisplus.annotation.DbType;
// MyBatis-Plus 拦截器总入口。
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
// 分页拦截器。
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// MyBatis-Plus 配置类。
// 调用关系：Spring 启动时加载，提供分页拦截器 Bean 给 SqlSessionFactory 使用。
@Configuration
public class MybatisPlusConfig {

    /**
     * 新版分页插件配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建拦截器主对象。
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 注册 MySQL 分页拦截器。
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 返回给 Spring 容器管理。
        return interceptor;
    }
}