package com.rgardner.simple_crud_application.security;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        try {
            UserDetails userDetails = User.withUsername("admin")
                    .password("admin")
                    .passwordEncoder(s -> passwordEncoder().encode(s))
                    .roles("ADMIN", "USER")
                    .build();
            jdbcUserDetailsManager.createUser(userDetails);
        }catch(Exception e){
            logger.info("{}", "Admin user has already been created in the database");
        }
        return jdbcUserDetailsManager;
    }
}
