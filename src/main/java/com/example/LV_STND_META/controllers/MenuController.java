package com.example.LV_STND_META.controllers;

import com.example.LV_STND_META.dto.MenuDto;
import com.example.LV_STND_META.entity.User;
import com.example.LV_STND_META.entity.UserKey;
import com.example.LV_STND_META.mappers.MenuuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuuMapper menuuMapper;

    @GetMapping("/start")
    public String showMenuList(Model model, HttpSession session) {
        // 로그인한 사용자의 userId 가져오기
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 로그인이 되어 있지 않은 경우 로그인 페이지로 이동
            return "redirect:/login"; // 로그인 페이지 경로를 적절히 변경해주세요.
        }

        UserKey userKey = user.getUserKey();
        String userId = userKey.getEmpNo();
        String compCd = userKey.getCompCd();
        String sysCd = "EIS";

        List<MenuDto> menuList = menuuMapper.selectMenu(userId, compCd, sysCd);

        model.addAttribute("menuList", menuList);
        return "start";
    }
}
