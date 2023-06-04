package snicorp.snd.migration.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@MapperScan(value = "snicorp.snd.migration.mapper.sandi",
        annotationClass= SandiConnMapper.class,
        sqlSessionFactoryRef="SandiSessionFactory")
@Configuration
public class SandiSQLConfig {

    @Bean(name = "SandiDataSource")
    @ConfigurationProperties(prefix="spring.datasource.sandi")
    public DataSource SandiDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "SandiSessionFactory")
    public SqlSessionFactory newSqlSessionFactory(
            @Qualifier("SandiDataSource")
            DataSource oracleDataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(oracleDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().
                        getResources("mappers/sandi/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("snicorp.snd.migration.dto.sandi");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "SandiSessionTemplate")
    public SqlSessionTemplate oracleSqlSessionTemplate(
            @Qualifier("SandiSessionFactory")
            SqlSessionFactory oracleSessionTemplate) {

        return new SqlSessionTemplate(oracleSessionTemplate);
    }


    @Bean(name = "SandiTransactionManager")
    public DataSourceTransactionManager PrimaryTransactionManager(
            @Qualifier("SandiDataSource")
            DataSource oracleDataSource) {

        return new DataSourceTransactionManager(oracleDataSource);
    }
}