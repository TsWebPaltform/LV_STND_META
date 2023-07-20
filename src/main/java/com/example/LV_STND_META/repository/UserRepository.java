package com.example.LV_STND_META.repository;


import com.example.LV_STND_META.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmpNo(String empNo);
}
