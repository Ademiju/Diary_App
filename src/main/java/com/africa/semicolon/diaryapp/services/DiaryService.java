package com.africa.semicolon.diaryapp.services;

import com.africa.semicolon.diaryapp.datas.models.Diary;
import com.africa.semicolon.diaryapp.datas.models.User;

public interface DiaryService {
    Diary createDiary(String title, User user);

}
