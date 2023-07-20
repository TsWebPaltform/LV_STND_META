package com.example.LV_STND_META.repository;


import com.example.LV_STND_META.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    // 필요한 쿼리 메소드를 추가할 수 있습니다.

}
