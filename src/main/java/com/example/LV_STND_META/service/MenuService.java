package com.example.LV_STND_META.service;

import com.example.LV_STND_META.mappers.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public void callSelectMenuProcedure() {
        String userId = "admin";
        String compCd = "DSF";
        String sysCd = "EIS";

        List<Map<String, Object>> result = menuMapper.callSelectMenuProcedure(userId, compCd, sysCd);

        // 결과 처리
        for (Map<String, Object> row : result) {
            // 각 행의 데이터 처리
            System.out.println(row);
        }
    }
}
