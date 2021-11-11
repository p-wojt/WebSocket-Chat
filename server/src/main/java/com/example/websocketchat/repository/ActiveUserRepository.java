package com.example.websocketchat.repository;

import com.example.websocketchat.entity.ActiveUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ActiveUserRepository extends JpaRepository<ActiveUserEntity, Long> {

    @Query(value = "SELECT c FROM ActiveUserEntity c WHERE c.nickname=?1")
    Optional<ActiveUserEntity> findByNickname(@Param("nickname") String nickname);
}
