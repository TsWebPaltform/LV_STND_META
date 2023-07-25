package com.example.LV_STND_META.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommonDto {
    @JsonProperty(value = "MST_CD")
    private String mstCd;

    @JsonProperty(value = "MST_NM")
    private String mstNm;
}
