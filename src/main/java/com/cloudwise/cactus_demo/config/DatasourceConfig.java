package com.cloudwise.cactus_demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean(name = "dataSourceTsb")
    @ConfigurationProperties(prefix = "spring.datasource.tsb") // application.properteis中对应属性的前缀
    @Primary
    public DataSource dataSourceBauschLomb() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean(name = "dataSourceCloudwise")
    @ConfigurationProperties(prefix = "spring.datasource.cloudwise") // application.properteis中对应属性的前缀
    public DataSource dataSourceCloudwise() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

}
