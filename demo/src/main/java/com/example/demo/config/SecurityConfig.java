package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                         .requestMatchers("/", "/login", "/join", "/id_search", "/pw_reset", "/loginProc", "/joinProc", "/mailSend", "/mailauthCheck", "/findUsername", "/users/findUsernameByEmail", "/resetPassword", "/usernameCheck", "/emailCheck").permitAll()
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
                         .defaultSuccessUrl("/main", true)
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
    
}
