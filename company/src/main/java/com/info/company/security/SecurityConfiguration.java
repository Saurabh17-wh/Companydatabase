package com.info.company.security;

import com.info.company.services.AuthenDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

  /*  @Autowired
    DataSource dataSource;*/

    /*@Autowired
    private AuthenDetailsService authenDetailsService;*/

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new AuthenDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        /*auth.jdbcAuthentication()                     //For taking authentication data from the database jdbc
                .dataSource(dataSource)
                .usersByUsernameQuery("select username , password ,enabled"
                + "from users"
                + "where username = ?")
                .authoritiesByUsernameQuery("select username , authority"
                + "from authorities"
                + "where username = ?");*/

        /*auth.inMemoryAuthentication()
                .withUser("user")
                .password(this.getPasswordEncoder().encode("pass"))
                //.password("pass")
                .roles("USER")
                .and()
                .withUser("admin")
                //.password("{noop}pass") // NoOpPasswordEncoder
                .password(this.getPasswordEncoder().encode("password"))
                //.password("password")
                .roles("ADMIN");*/

            auth.authenticationProvider(authenticationProvider());

    }


    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER" , "ADMIN")
                //.antMatchers("/").permitAll()
                .and()
                .httpBasic();
    }
}
