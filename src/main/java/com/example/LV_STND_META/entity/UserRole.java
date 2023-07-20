package com.example.LV_STND_META.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_SYS_ROLE_BY_USR_MNG")
public class UserRole {

    @Id
    @Column(name = "COMP_CD")
    private String companyCode;

    @Column(name = "SYS_CD")
    private String systemCode;

    @Column(name = "ROLE_CD")
    private String roleCode;

    @Column(name = "EMP_NO")
    private String empNo;

    @Column(name = "CRTP_NM")
    private String createdBy;

    @Column(name = "CRT_DTTM")
    private LocalDateTime createdDatetime;

    @Column(name = "CHGP_NM")
    private String modifiedBy;

    @Column(name = "CHG_DTTM")
    private LocalDateTime modifiedDatetime;

    // 생성자, getter, setter 등 생략
}
