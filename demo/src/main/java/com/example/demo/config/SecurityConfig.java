package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     // BCryptPasswordEncoder를 Bean으로 등록하여 비밀번호 암호화에 사용
     @Bean
     public BCryptPasswordEncoder bCryptPasswordEncoder() {
         return new BCryptPasswordEncoder();
     }
 
     // SecurityFilterChain을 Bean으로 등록하여 Spring Security 설정을 구성
     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 // HTTP 요청에 대한 인증 및 권한 설정
                 .authorizeHttpRequests((auth) -> auth
                         // 특정 경로는 모든 사용자에게 허용
                         .requestMatchers("/", "/login", "/join", "/id_search", "/pw_reset", "/loginProc", "/joinProc", "/mailSend", "/mailauthCheck", "/findUsername", "/users/findUsernameByEmail", "/resetPassword", "/usernameCheck", "/emailCheck", "/notices/add", "/notices/form", "/notices/list").permitAll()
                         .requestMatchers("/notices/add", "/notices/edit/**").hasRole("ADMIN")
                         .requestMatchers("/notices/**").authenticated()
                         // 그 외의 모든 요청은 인증된 사용자에게만 허용
                         .anyRequest().authenticated()
                 )
                 // 로그인 설정
                 .formLogin((auth) -> auth
                         // 로그인 페이지의 경로 설정
                         .loginPage("/login")
                         // 로그인 처리 URL 설정
                         .loginProcessingUrl("/loginProc")
                         // 로그인 성공 후 이동할 기본 경로 설정
                         .successHandler(customAuthenticationSuccessHandler())
                         // 로그인 실패 시 이동할 경로 설정
                         .failureUrl("/login?error=true")
                         // 모든 사용자에게 로그인 페이지 접근 허용
                         .permitAll()
                 )
                 // CSRF 보호 비활성화
                 .csrf((auth) -> auth.disable());
 
         // 구성된 HttpSecurity 객체를 빌드하여 반환
         return http.build();
    }

    // 사용자 정의 인증 성공 핸들러 Bean 등록
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    // CustomAuthenticationSuccessHandler 클래스 정의
    public static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
                                            org.springframework.security.core.Authentication authentication) 
                                            throws IOException, ServletException {
            // 사용자 역할에 따른 리다이렉션 로직
            String role = authentication.getAuthorities().stream()
                                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                                        .filter(authority -> authority.equals("admin"))
                                        .findFirst()
                                        .orElse("ROLE_USER");

            if ("admin".equals(role)) {
                response.sendRedirect("/notice_form");
            } else {
                response.sendRedirect("/main");
            }
        }
    }
    
}
