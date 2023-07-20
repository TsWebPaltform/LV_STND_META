package com.example.LV_STND_META.controllers;

import com.example.LV_STND_META.UserLoginRequest;
import com.example.LV_STND_META.entity.User;
import com.example.LV_STND_META.entity.UserRole;
import com.example.LV_STND_META.mappers.MenuMapper;
import com.example.LV_STND_META.repository.UserRepository;
import com.example.LV_STND_META.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/lv_main")
    public String showLv_mainForm() {
        return "lv_main";
    }

    @GetMapping("/main")
    public String showMainForm() {
        return "main";
    }

    @PostMapping("/login")
    public ResponseEntity<String> processLoginForm(@RequestBody UserLoginRequest loginRequest, HttpSession session) {
        String empNo = loginRequest.getEmpNo();
        String password = loginRequest.getPassword();

        User user = userRepository.findByEmpNo(empNo);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // 로그인 성공
            session.setAttribute("user", user);
            return ResponseEntity.ok().body("/success");
        } else {
            // 로그인 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @GetMapping("/success")
    public String showSuccessForm(Model model) {
        // 로그인 상태가 아니면 로그인 페이지로 리다이렉트
        if (!isLoggedIn()) {
            return "redirect:/login";
        }

        List<UserRole> userRoles = userRoleRepository.findAll();
        model.addAttribute("userRoles", userRoles);
        return "success";
    }

    private boolean isLoggedIn() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // 세션에 "user" 속성이 존재하면 로그인 상태로 간주합니다
            return true;
        }
        return false;
    }



}
