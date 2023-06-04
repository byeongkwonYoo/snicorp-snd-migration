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

@MapperScan(value = "snicorp.snd.migration.mapper.old",
        annotationClass= OldConnMapper.class,
        sqlSessionFactoryRef="OldSessionFactory")
@Configuration
public class OldSQLConfig {

    @Bean(name = "OldDataSource")
    @ConfigurationProperties(prefix="spring.datasource.old")
    public DataSource OldDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "OldSessionFactory")
    public SqlSessionFactory oldSqlSessionFactory(
            @Qualifier("OldDataSource")
            DataSource oracleDataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(oracleDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().
                        getResources("mappers/old/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("snicorp.snd.migration.dto.old");

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "OldSessionTemplate")
    public SqlSessionTemplate oracleSqlSessionTemplate(
            @Qualifier("OldSessionFactory")
            SqlSessionFactory oracleSessionTemplate) {

        return new SqlSessionTemplate(oracleSessionTemplate);
    }


    @Bean(name = "OldTransactionManager")
    public DataSourceTransactionManager PrimaryTransactionManager(
            @Qualifier("OldDataSource")
            DataSource oracleDataSource) {

        return new DataSourceTransactionManager(oracleDataSource);
    }
}