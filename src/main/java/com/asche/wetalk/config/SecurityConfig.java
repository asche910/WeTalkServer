package com.asche.wetalk.config;

import com.asche.wetalk.other.CustomAuthenticationProvider;
import com.asche.wetalk.other.JWTAuthenticationFilter;
import com.asche.wetalk.other.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 关闭csrf验证
//        http.csrf().disable()
//                // 对请求进行认证
//                .authorizeRequests()
//                // 所有 / 的所有请求 都放行
//                .antMatchers("/", "/login").permitAll()
//                // 所有 /login 的POST请求 都放行
//                .antMatchers(HttpMethod.POST, "/loginCheck").permitAll()
//                // 添加权限检测
//                .antMatchers("/fileUpload").hasAuthority("AUTH_WRITE")
//                // 角色检测
//                .antMatchers("/vip").hasRole("ADMIN")
//                // 所有请求需要身份认证
//                .anyRequest().authenticated();
//    /*            .and()
//                // 添加一个过滤器验证其他请求的Token是否合法
//                .addFilterBefore(new JWTAuthenticationFilter(),
//                        UsernamePasswordAuthenticationFilter.class);*/
//    }

    /**
     * this function will invoked if the function above is commented!
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }
}
