package com.lvzuan.meetmanager.config;

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
@MapperScan({"com.lvzuan.meetmanager.mapper"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}