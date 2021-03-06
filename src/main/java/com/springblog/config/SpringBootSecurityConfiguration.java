package com.springblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringBootSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
    }
    
//            http
//                    .authorizeRequests()
//                    .antMatchers("/","/registration","/login", "/blog").permitAll()
//                    .antMatchers("/blog/**","/blog/comment/**").hasAnyRole("USER")
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/blog").permitAll()
//                .and()
//                    .logout().permitAll();
//}

}
