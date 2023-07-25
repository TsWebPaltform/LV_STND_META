package com.example.LV_STND_META.mappers;

import com.example.LV_STND_META.dto.CommonDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface MyMapper {
    // 기존 메서드들과 함께 프로시저를 호출하는 메서드를 추가합니다.
    @Select("EXEC DBO.USP_SELECT_COMPANY")
    @Results({
            @Result(property = "mstCd", column = "MST_CD"),
            @Result(property = "mstNm", column = "MST_NM")
    })
    List<CommonDto> getCompList();
}