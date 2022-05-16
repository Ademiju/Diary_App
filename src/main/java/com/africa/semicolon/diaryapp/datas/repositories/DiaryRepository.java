package com.africa.semicolon.diaryapp.datas.repositories;

import com.africa.semicolon.diaryapp.datas.models.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
