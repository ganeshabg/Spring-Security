package com.example.springsecuritydatabase.config;

import com.example.springsecuritydatabase.permission.CustomPermissionEvaluator;
import com.example.springsecuritydatabase.service.UserDetailSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailSecurityService userDetailsService;

    @Autowired
    private CustomPermissionEvaluator permissionEvaluator;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/console/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

    }

    @Bean
    public AuthenticationProvider  authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return  provider;
    }

//    @Bean
//    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(){
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(permissionEvaluator);
//        return  expressionHandler;
//    }

}
