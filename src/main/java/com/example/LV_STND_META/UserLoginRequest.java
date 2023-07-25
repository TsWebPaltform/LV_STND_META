package com.example.LV_STND_META;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String empNo;
    private String password;
    private String compCd;

    // 생성자, getter, setter 생략
}
