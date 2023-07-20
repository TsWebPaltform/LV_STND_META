package com.example.LV_STND_META.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MenuDto {
    @JsonProperty("SCR_ID")
    private String SCR_ID = "";

    @JsonProperty("MENU_CD")
    private String MENU_CD= "";

    @JsonProperty("MENU_LANG_NM")
    private String MENU_LANG_NM= "";

    @JsonProperty("LVL_1_CD")
    private String LVL_1_CD= "";

    @JsonProperty("LVL_1_NM")
    private String LVL_1_NM= "";

    @JsonProperty("LVL_2_CD")
    private String LVL_2_CD= "";

    @JsonProperty("LVL_2_NM")
    private String LVL_2_NM= "";

    @JsonProperty("LVL_3_CD")
    private String LVL_3_CD= "";

    @JsonProperty("LVL_3_NM")
    private String LVL_3_NM= "";

    @JsonProperty("LVL_4_CD")
    private String LVL_4_CD= "";

    @JsonProperty("LVL_4_NM")
    private String LVL_4_NM= "";

    @JsonProperty("LVL_5_CD")
    private String LVL_5_CD= "";

    @JsonProperty("LVL_5_NM")
    private String LVL_5_NM= "";

    @JsonProperty("PDF_YN")
    private String PDF_YN= "";

    @JsonProperty("EXCEL_YN")
    private String EXCEL_YN= "";

    @JsonProperty("PPT_YN")
    private String PPT_YN= "";

    @JsonProperty("CNF_YN")
    private String CNF_YN= "";

    @JsonProperty("INFO_YN")
    private String INFO_YN= "";

    @JsonProperty("LV_YN")
    private String LV_YN= "";

    @JsonProperty("SCR_DOM_SEQ")
    private int SCR_DOM_SEQ;

    @JsonProperty("MENU_SRT_SEQ")
    private int MENU_SRT_SEQ;

    @JsonProperty("SCR_SRT_SEQ")
    private int SCR_SRT_SEQ;

    // Getters and Setters
}
