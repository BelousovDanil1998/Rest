package Belousov.Spring.SpringSecurity.config;



import Belousov.Spring.SpringSecurity.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

    private final LoginSuccessHandler loginSuccessHandler;
    private final UserServiceImpl userServiceImpl;

    public securityConfig(LoginSuccessHandler loginSuccessHandler, UserServiceImpl userServiceImpl) {
        this.loginSuccessHandler = loginSuccessHandler;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email_address")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();

//        http.csrf().disable()
//                .authorizeRequests()
//                //страницы аутентификации доступна всем
//                .antMatchers("/login", "/auth/registration").permitAll()
//                // защищенные URL
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login")
//                .loginProcessingUrl("/login")
//                .successHandler(loginSuccessHandler)
//                .failureUrl("/login?error")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
