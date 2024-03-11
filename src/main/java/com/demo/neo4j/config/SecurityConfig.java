package com.demo.neo4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable();
		http.authorizeRequests()
			.antMatchers("/admin/home/index").permitAll()
			.antMatchers("/order/**", 
					"/account/edit-profile",
					"/account/change-password",
					"/spring/logout").authenticated()
			.anyRequest().permitAll();
		
		// => từ chối nếu truy cập với vai trò không hợp lệ
		http.exceptionHandling()
			.accessDeniedPage("/security/access/denied");
		
		// => cấu hình form đăng nhập và đăng xuất
		http.formLogin()
			.loginProcessingUrl("/spring/login")
			.loginPage("/security/login/form")
			.defaultSuccessUrl("/security/login/success")
			.failureUrl("/security/login/failure");	
		http.rememberMe()
			.tokenValiditySeconds(5 * 24 * 60 * 60);
		
		http.logout()
			.logoutUrl("/spring/logout")
			.logoutSuccessUrl("/security/logout/success")
			.addLogoutHandler(new SecurityContextLogoutHandler());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
