package com.example.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.login.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
   
    @Bean
    @Order(1)
    SecurityFilterChain swaggerUiChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher(
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v3/api-docs/**"
            )
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().hasRole("ADMIN")
            );

        return http.build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain webAndApiChain(HttpSecurity http) throws Exception {

        http
            // 🔥 Tắt CSRF cho API
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**")
            )

            .authorizeHttpRequests(auth -> auth

                // Public
                .requestMatchers("/", "/login", "/register",
                        "/css/**", "/js/**", "/images/**").permitAll()

                // Web page chỉ ADMIN
                .requestMatchers("/computers/edit/**",
                        "/computers/delete/**").hasRole("ADMIN")

                // API
                .requestMatchers(org.springframework.http.HttpMethod.GET,
                        "/api/computers/image/**").permitAll()

                .requestMatchers(org.springframework.http.HttpMethod.DELETE,
                        "/api/computers/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.POST,
                        "/api/computers/**").hasRole("ADMIN")

                // Các request còn lại cần login
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder builder =
            http.getSharedObject(AuthenticationManagerBuilder.class);

        builder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

        return builder.build();
    }
}
