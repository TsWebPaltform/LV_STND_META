package com.example.LV_STND_META.controllers;

import com.example.LV_STND_META.dto.MenuDto;
import com.example.LV_STND_META.entity.User;
import com.example.LV_STND_META.entity.UserKey;
import com.example.LV_STND_META.mappers.MenuuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MenuRestController {
    private final MenuuMapper menuuMapper;

    @Autowired
    public MenuRestController(MenuuMapper menuuMapper) {
        this.menuuMapper = menuuMapper;
    }

    @PostMapping("/menu")
    @ResponseBody
    public List<MenuDto> getMenuList(HttpSession session) {
        // 로그인한 사용자의 userId 가져오기
        User user = (User) session.getAttribute("user");
        UserKey userKey = user.getUserKey();

        String userId = userKey.getEmpNo();
        String compCd = userKey.getCompCd();
        String sysCd = "EIS";


        return menuuMapper.selectMenu(userId, compCd, sysCd);
    }

}
