package com.africa.semicolon.diaryapp.services;

import com.africa.semicolon.diaryapp.datas.models.Diary;
import com.africa.semicolon.diaryapp.datas.models.User;
import com.africa.semicolon.diaryapp.datas.repositories.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DairyServiceImpl implements DiaryService{
    @Autowired
    DiaryRepository diaryRepository;


    @Override
    public Diary createDiary(String title, User user) {
        Diary diary = new Diary(title);
        diary.setUser(user);
        return diaryRepository.save(diary);
    }
}
