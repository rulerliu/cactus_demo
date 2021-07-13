package com.cloudwise.cactus_demo.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.cloudwise.cactus_demo.mapper.cw"}, sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MybatisCloudwiseConfig {

    @Autowired
    @Qualifier("dataSourceCloudwise")
    private DataSource cloudwiseDS;

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(cloudwiseDS); // 使用本地据源, 连接本地的库
//        return factoryBean.getObject();
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(cloudwiseDS);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2()); // 使用上面配置的Factory
        return template;
    }
}
