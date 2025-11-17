package org.easybook.booking.config;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.helidon.config.Config;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.sql.DataSource;

@ApplicationScoped
public class DatabaseConfig {

    private final Config config;

    @Inject
    public DatabaseConfig(Config config) {
        this.config = config;
    }

    @Produces
    @Singleton
    public SQLQueryFactory queryFactory(DataSource dataSource) {
        Configuration configuration = new Configuration(new PostgreSQLTemplates());
        return new SQLQueryFactory(configuration, dataSource);
    }

    @Produces
    @ApplicationScoped
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        Config jdbcConfig = config.get("dataSource");
        hikariConfig.setJdbcUrl(jdbcConfig.get("url").asString().orElseThrow());
        hikariConfig.setUsername(jdbcConfig.get("user").asString().orElseThrow());
        hikariConfig.setPassword(jdbcConfig.get("password").asString().orElseThrow());

        return new HikariDataSource(hikariConfig);
    }
}
