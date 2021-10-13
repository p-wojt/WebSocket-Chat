package com.example.websocketchat.repository;

import com.example.websocketchat.entity.ActiveUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActiveUserRepository extends JpaRepository<ActiveUserEntity, Long> {
    @Query(value = "DELETE FROM ActiveUserEntity c WHERE c.nickname =: nickname")
    void deleteUserByNickname(@Param("nickname") String nickname);
}
