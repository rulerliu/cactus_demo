package com.cloudwise.cactus_demo.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.cloudwise.cactus_demo.mapper.tsb"}, sqlSessionTemplateRef = "sqlSessionTemplate1")
public class MybatisTsbConfig {

    @Autowired
    @Qualifier("dataSourceTsb")
    private DataSource dataSourceTsb;
    @Autowired
    private MybatisPlusInterceptor mybatisPlusInterceptor;

    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(tsbDS); // 使用博士伦数据源, 连接博士伦的库
//        return factoryBean.getObject();

        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceTsb);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
        factoryBean.setTypeAliasesPackage("com.cloudwise.cactus_demo.pojo.*");
        // 导入mybatis配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        // 配置打印sql语句
        configuration.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(configuration);
        // 添加分页功能
        factoryBean.setPlugins(new Interceptor[]{
                mybatisPlusInterceptor
        });
        // 导入全局配置
//        factoryBean.setGlobalConfig(globalConfiguration());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
        return template;
    }
}
