package com.example.LV_STND_META.repository;


import com.example.LV_STND_META.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
//        User findByEmpNoAndCompCd(String empNo, String compCd);
//    User findByEmpNo(String empNo);

        User findByUserKeyEmpNoAndUserKeyCompCd(String empNo, String compCd);
}
