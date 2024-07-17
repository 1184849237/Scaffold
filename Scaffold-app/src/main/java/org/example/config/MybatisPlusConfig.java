package org.example.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Admin
 * @ClassName: cc
 * @Description: mybatis-plus 配置类
 * @Create by: 周鹏程
 * @Date: 2020/11/26 18:33
 */
@Configuration
@MapperScan({"org.example.domain.word.repository"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}