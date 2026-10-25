package com.example.accounts.Config;

import com.example.accounts.Service.ServiceImp.ImpCustomUserDetailsService;
import com.example.accounts.securites.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    final private JwtAuthenticationFilter jwtFilter;
    public SecurityConfiguration(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http.authorizeHttpRequests(authorizeRequests ->
             authorizeRequests.requestMatchers("/api/account/create").authenticated()
                     .requestMatchers(
                             "/api/user/register",
                             "/api/user/login"
                     ).permitAll()

                     .anyRequest().permitAll()


             ).sessionManagement(sessionManagement ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
             .formLogin(Customizer.withDefaults())
             .httpBasic(Customizer.withDefaults())
             .csrf(AbstractHttpConfigurer::disable);

     return http.build();
 }

 @Bean
    public BCryptPasswordEncoder  getEncoder(){
     return new BCryptPasswordEncoder();
 }
 @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception{
     return config.getAuthenticationManager();
 }
 @Bean
    public AuthenticationProvider getAuthenticationProvider(ImpCustomUserDetailsService  service, PasswordEncoder encoder){
     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(service)
             ;
     daoAuthenticationProvider.setPasswordEncoder(encoder);
     return daoAuthenticationProvider;

 }
}
