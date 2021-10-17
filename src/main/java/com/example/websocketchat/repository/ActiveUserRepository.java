package com.example.websocketchat.repository;

import com.example.websocketchat.entity.ActiveUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ActiveUserRepository extends JpaRepository<ActiveUserEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ActiveUserEntity c WHERE c.nickname=?1")
    void deleteUserByNickname(@Param("nickname") String nickname);

    @Query(value = "SELECT c FROM ActiveUserEntity c WHERE c.nickname=?1")
    ActiveUserEntity findByNickname(@Param("nickname") String nickname);
}
