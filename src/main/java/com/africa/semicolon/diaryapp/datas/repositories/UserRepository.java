package com.africa.semicolon.diaryapp.datas.repositories;

import com.africa.semicolon.diaryapp.datas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
}
