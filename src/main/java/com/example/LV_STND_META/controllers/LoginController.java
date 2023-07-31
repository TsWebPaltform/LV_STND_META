package com.example.LV_STND_META.controllers;

import com.example.LV_STND_META.UserLoginRequest;
import com.example.LV_STND_META.dto.CommonDto;
import com.example.LV_STND_META.entity.User;
import com.example.LV_STND_META.entity.UserRole;
import com.example.LV_STND_META.mappers.MenuMapper;
import com.example.LV_STND_META.mappers.MyMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MyMapper myMapper; // My_Mapper Autowired

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        List<CommonDto> compList = myMapper.getCompList();

        model.addAttribute("compList", compList);
        return "login";
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm() {
        if (!isLoggedIn()) {
            return "redirect:/login";
        }
        return "change-password";
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
        String compCd = loginRequest.getCompCd();

        User user = userRepository.findByUserKeyEmpNoAndUserKeyCompCd(empNo, compCd);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {

            // 로그인 성공
            session.setAttribute("user", user);
            session.setAttribute("compCd", compCd);
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


    @PostMapping("/change-password")
    public ResponseEntity<String> processChangePasswordForm(@RequestParam("currentPassword") String currentPassword,
                                                            @RequestParam("newPassword") String newPassword,
                                                            @RequestParam("confirmNewPassword") String confirmNewPassword,
                                                            HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        String empNo = user.getUserKey().getEmpNo();
        String compCd = user.getUserKey().getCompCd();

        User fetchedUser = userRepository.findByUserKeyEmpNoAndUserKeyCompCd(empNo, compCd);

        if (fetchedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 현재 비밀번호가 맞는지 확인
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            String alertMessage = "현재 비밀번호가 올바르지 않습니다.";
            String loginLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/change-password").toUriString();
            String script = "<script>alert('" + alertMessage + "'); window.location.href='" + loginLink + "';</script>";
            return ResponseEntity.ok().body(script);
        }

        // 새로운 비밀번호와 확인용 비밀번호가 일치하는지 확인
        if (!newPassword.equals(confirmNewPassword)) {
            String alertMessage = "새로운 비밀번호가 일치하지 않습니다.";
            String loginLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/change-password").toUriString();
            String script = "<script>alert('" + alertMessage + "'); window.location.href='" + loginLink + "';</script>";
            return ResponseEntity.ok().body(script);
        }


//        // 새로운 비밀번호가 강도를 만족하는지 확인 (예시로 비밀번호 길이를 8자 이상으로 설정)
//        if (newPassword.length() < 8) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be at least 8 characters long");
//        }

        // 이전 비밀번호와 새로운 비밀번호가 같은지 확인
        if (passwordEncoder.matches(newPassword, user.getPreviousPassword())) {
            String alertMessage = "새로운 비밀번호는 이전에 사용한 비밀번호와 같을 수 없습니다.";
            String changePasswordLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/change-password").toUriString();
            String script = "<script>alert('" + alertMessage + "'); window.location.href='" + changePasswordLink + "';</script>";
            return ResponseEntity.ok().body(script);
        }

        // 비밀번호 변경 시점과 이전 비밀번호 업데이트
        user.setPreviousPassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        String alertMessage = "비밀번호 변경이 완료되었습니다.";
        String loginLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/login").toUriString();
        String script = "<script>alert('" + alertMessage + "'); window.location.href='" + loginLink + "';</script>";
        return ResponseEntity.ok().body(script);
    }

}
