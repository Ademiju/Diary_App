package com.africa.semicolon.diaryapp.dtos.responses;

import com.africa.semicolon.diaryapp.datas.models.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {
    private  Long id;
    private  String email;
    private Set<Diary> diaries;
}
