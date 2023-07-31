package com.example.LV_STND_META.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "TB_SYS_USR_MNG")
public class User{

    @EmbeddedId
    private UserKey userKey;

    @Column(name = "EMP_NM")
    private String empName;

    @Column(name = "PW")
    private String password;

    @Column(name = "ORG_CD")
    private String orgCode;

    @Column(name = "ORG_NM")
    private String orgName;

    @Column(name = "POST_CD")
    private String postCode;

    @Column(name = "POST_NM")
    private String postName;

    @Column(name = "DUTY_CD")
    private String dutyCode;

    @Column(name = "DUTY_NM")
    private String dutyName;

    @Column(name = "EMAIL_ADD")
    private String emailAddress;

    @Column(name = "CRTP_NM")
    private String createdBy;

    @Column(name = "CRT_DTTM")
    private Date createdDatetime;

    @Column(name = "CHGP_NM")
    private String modifiedBy;

    @Column(name = "CHG_DTTM")
    private Date modifiedDatetime;

    @Column(name = "PREV_PW")
    private String previousPassword;

    // 생성자, getter, setter 등 생략

}
