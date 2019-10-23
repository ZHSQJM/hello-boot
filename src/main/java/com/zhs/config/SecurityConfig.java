package com.zhs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/16 15:50
 * @Description:
 * @version: 1.0
 */
@Configuration
 @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("zhs").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
       // auth.userDetailsService(userDetailsService);
    }


    @Override
      protected void configure(HttpSecurity http) throws Exception {
       http
       .authorizeRequests()
               .antMatchers("/**").permitAll();
       http.csrf().disable();
       // 以css和index不需要验证
        //.antMatchers("/css/**","/index").permitAll()
         // .antMatchers("/user/**").hasRole("USER")
              //  .antMatchers("/role/**").hasRole("USER")
          //  .and()
              //  表单登录的地址是/login 登录失败的是/login-error
           //  .formLogin().loginPage("/login").failureUrl("/login-error")
          //  .and()
             //   异常处理会重定向到401
            //.exceptionHandling().accessDeniedPage("/401");
        http.logout().logoutSuccessUrl("/");
     }
}
