package com.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan({ "com.service*" })
public class SecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')").and().
                authorizeRequests().antMatchers("/home/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')").
                and().formLogin().  //login configuration
                loginPage("/customLogin.xhtml").
                loginProcessingUrl("/appLogin").
                usernameParameter("login").
                passwordParameter("password").
                defaultSuccessUrl("/home/welcome.xhtml").
                and().logout().    //logout configuration
                logoutUrl("/appLogout").
                logoutSuccessUrl("/customLogin.xhtml").and().
                exceptionHandling().accessDeniedPage("/403.xhtml");


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}

