package com.SberProjectUEN.java13springTU.libraryproject.config;


import com.SberProjectUEN.java13springTU.libraryproject.service.userdetails.CustomUserDetails;
import com.SberProjectUEN.java13springTU.libraryproject.service.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;

import static com.SberProjectUEN.java13springTU.libraryproject.constants.SecurityConstants.*;
import static com.SberProjectUEN.java13springTU.libraryproject.constants.UserRolesConstants.ADMIN;
import static com.SberProjectUEN.java13springTU.libraryproject.constants.UserRolesConstants.LIBRARIAN;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
                             CustomUserDetailsService customUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    //https://docs.spring.io/spring-security/reference/servlet/exploits/firewall.html
    @Bean
    public HttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        return firewall;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//              csrf().disable()
              .csrf(csrf -> csrf
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                   )
              //Настройка http запросов - кому куда можно/нельзя
              .authorizeHttpRequests((requests) -> requests
                                           .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                                           .requestMatchers(BOOKS_WHITE_LIST.toArray(String[]::new)).permitAll()
                                           .requestMatchers(USERS_WHITE_LIST.toArray(String[]::new)).permitAll()
                                           .requestMatchers(BOOKS_PERMISSION_LIST.toArray(String[]::new)).hasAnyRole(ADMIN, LIBRARIAN)
                                           .anyRequest().authenticated()
                                    )
              //Настройка для входа в систему
              .formLogin((form) -> form
                               .loginPage("/login")
                               //Перенаправление на главную страницу после успешной авторизации
                               .defaultSuccessUrl("/")
                               .permitAll()
                        )
              .logout((logout) -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            .permitAll()
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                     );
        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}

