package com.example.demo.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-info")//현재 로그인 된 사용자의 정보를 가져와 모델에 추가
    public ModelAndView getUserInfo() {
        UserEntity user = userService.getCurrentUser();
        ModelAndView mav = new ModelAndView("user_infor");//해당정보를 user_infor에 표시
        mav.addObject("user", user);
        return mav;
    }
    //사용자 정보를 업데이트하는 메소드
    @PostMapping("/update-user-info")
    public ModelAndView updateUserInfo(@RequestParam Long id, @RequestParam String name, @RequestParam String sex,
     @RequestParam Integer age, @RequestParam String introduce) {
        UserEntity user = userService.getCurrentUser();
        if (user != null) {//사용자의 정보가 null이 아닌경우에 업데이트수행
            user.setName(name);
            user.setSex(sex);
            userService.updateUser(user);
            //사용자의 정보들을 업데이트
        }
        return new ModelAndView("redirect:/users/user-info");
        //업데이트 후 사용자 정보를 보여주는 페이지로 리디렉션
    }
    //사용자를 탈퇴시키는 메소드
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawUser(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request, HttpServletResponse response) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        String username = userDetails.getUsername();
        boolean success = userService.withdrawUserByUsername(username);
        if (success) {
            // 로그아웃 처리
            new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            return ResponseEntity.ok("User withdrawal successful.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User withdrawal failed.");
        }
    }

    //이메일로 사용자이름을 찾는 메소드
    @GetMapping("/findUsernameByEmail")
    public ResponseEntity<?> findUsernameByEmail(@RequestParam String email) {
        String username = userService.findUsernameByEmail(email);
        if (username != null) {
            return ResponseEntity.ok(Collections.singletonMap("username", username));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"User not found\"}");
        }
    }
}
