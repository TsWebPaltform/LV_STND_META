package com.example.LV_STND_META.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.example.LV_STND_META.dto.MenuDto; // 수정된 부분: MenuDto import 추가

import java.util.List;

@Mapper
public interface MenuuMapper {
    @Results({
            @Result(property = "SCR_ID", column = "SCR_ID"),
            @Result(property = "MENU_CD", column = "MENU_CD"),
            @Result(property = "MENU_LANG_NM", column = "MENU_LANG_NM"),
            @Result(property = "LVL_1_CD", column = "LVL_1_CD"),
            @Result(property = "LVL_1_NM", column = "LVL_1_NM"),
            @Result(property = "LVL_2_CD", column = "LVL_2_CD"),
            @Result(property = "LVL_2_NM", column = "LVL_2_NM"),
            @Result(property = "LVL_3_CD", column = "LVL_3_CD"),
            @Result(property = "LVL_3_NM", column = "LVL_3_NM"),
            @Result(property = "LVL_4_CD", column = "LVL_4_CD"),
            @Result(property = "LVL_4_NM", column = "LVL_4_NM"),
            @Result(property = "LVL_5_CD", column = "LVL_5_CD"),
            @Result(property = "LVL_5_NM", column = "LVL_5_NM"),
            @Result(property = "PDF_YN", column = "PDF_YN"),
            @Result(property = "EXCEL_YN", column = "EXCEL_YN"),
            @Result(property = "PPT_YN", column = "PPT_YN"),
            @Result(property = "CNF_YN", column = "CNF_YN"),
            @Result(property = "INFO_YN", column = "INFO_YN"),
            @Result(property = "LV_YN", column = "LV_YN"),
            @Result(property = "SCR_DOM_SEQ", column = "SCR_DOM_SEQ"),
            @Result(property = "MENU_SRT_SEQ", column = "MENU_SRT_SEQ"),
            @Result(property = "SCR_SRT_SEQ", column = "SCR_SRT_SEQ")
    })
    @Select("{CALL USP_SELECT_MENU(#{userId}, #{compCd}, #{sysCd, mode=IN})}")
    List<MenuDto> selectMenu(@Param("userId") String userId, @Param("compCd") String compCd, @Param("sysCd") String sysCd);
}
