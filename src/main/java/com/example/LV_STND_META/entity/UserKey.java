package com.example.LV_STND_META.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserKey implements Serializable {

    @Column(name = "EMP_NO")
    private String empNo;

    @Column(name = "COMP_CD")
    private String compCd;
}

