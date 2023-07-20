package com.example.LV_STND_META.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {
    List<Map<String, Object>> callSelectMenuProcedure(
            @Param("userId") String userId,
            @Param("compCd") String compCd,
            @Param("sysCd") String sysCd
    );
}
