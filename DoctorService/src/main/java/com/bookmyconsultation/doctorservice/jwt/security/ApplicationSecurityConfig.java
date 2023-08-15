package com.bookmyconsultation.doctorservice.jwt.security;

import com.bookmyconsultation.doctorservice.jwt.ApplicationUserDetailsService;
import com.bookmyconsultation.doctorservice.jwt.JWTAuthenticationFilter;
import com.bookmyconsultation.doctorservice.jwt.JWTTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.bookmyconsultation.doctorservice.jwt.ApplicationRole.ADMIN;
import static com.bookmyconsultation.doctorservice.jwt.ApplicationRole.USER;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserDetailsService applicationUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JWTTokenVerifier(),JWTAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/","/index.html").permitAll()
                .antMatchers("/doctors/**").hasAnyRole(USER.name(),ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/doctors/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(applicationUserDetailsService);
        return provider;
    }


}
