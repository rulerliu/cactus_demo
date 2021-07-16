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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.cloudwise.cactus_demo.mapper.cw"}, sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MybatisCloudwiseConfig {

    @Autowired
    @Qualifier("dataSourceCloudwise")
    DataSource cloudwiseDS;
    @Autowired
    private MybatisPlusInterceptor mybatisPlusInterceptor;

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(cloudwiseDS); // 使用本地据源, 连接本地的库
//        return factoryBean.getObject();

        // 导入mybatissqlsession配置
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(cloudwiseDS);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
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
    @Primary
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2()); // 使用上面配置的Factory
        return template;
    }
}
