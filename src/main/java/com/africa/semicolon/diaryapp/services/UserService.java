package com.africa.semicolon.diaryapp.services;

import com.africa.semicolon.diaryapp.datas.models.Diary;
import com.africa.semicolon.diaryapp.datas.models.User;
import com.africa.semicolon.diaryapp.dtos.requests.UserRequest;
import com.africa.semicolon.diaryapp.dtos.responses.UserResponse;
import com.africa.semicolon.diaryapp.exceptions.DiaryAppException;

import javax.validation.constraints.NotNull;

public interface UserService {
    UserResponse createUser(UserRequest userRequest) throws DiaryAppException;
    Diary addDiary(@NotNull Long id, @NotNull Diary diary) throws DiaryAppException;

    User findById(Long userId) throws DiaryAppException;
    boolean deleteUser(User user);

    User findUserByEmail(String email);
}
