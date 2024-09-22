package com.archivos.apispringbootcunoc.config;


import com.archivos.apispringbootcunoc.config.filter.JwtTokenValidator;
import com.archivos.apispringbootcunoc.service.UserDetailServiceImpl;
import com.archivos.apispringbootcunoc.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtUtils jwtUtils) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/home/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/cashier/**").hasAnyRole("CASHIER");
                    http.requestMatchers(HttpMethod.POST, "/cashier/**").hasAnyRole("CASHIER");
                    http.requestMatchers(HttpMethod.PUT, "/cashier/**").hasAnyRole("CASHIER");
                    http.requestMatchers(HttpMethod.GET, "/warehouse/**").hasAnyRole("WAREHOUSE");
                    http.requestMatchers(HttpMethod.POST, "/warehouse/**").hasAnyRole("WAREHOUSE");
                    http.requestMatchers(HttpMethod.PUT, "/warehouse/**").hasAnyRole("WAREHOUSE");
                    http.requestMatchers(HttpMethod.DELETE, "/warehouse/**").hasAnyRole("WAREHOUSE");
                    http.requestMatchers(HttpMethod.GET, "/inventory/**").hasAnyRole("INVENTORY");
                    http.requestMatchers(HttpMethod.POST, "/inventory/**").hasAnyRole("INVENTORY");
                    http.requestMatchers(HttpMethod.GET, "/methods/get").hasAnyRole("CASHIER");
                    http.requestMatchers(HttpMethod.POST, "/methods/post").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PATCH, "/methods/patch").hasAnyAuthority("REFACTOR");
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.addHeader("Set-Cookie", "jwtToken=; HttpOnly; Secure; Path=/; Max-Age=0; SameSite=Strict");
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("jwtToken")
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
                        })
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
