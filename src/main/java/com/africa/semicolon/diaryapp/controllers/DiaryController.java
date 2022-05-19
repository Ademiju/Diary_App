package com.africa.semicolon.diaryapp.controllers;

import com.africa.semicolon.diaryapp.datas.models.Diary;
import com.africa.semicolon.diaryapp.datas.models.User;
import com.africa.semicolon.diaryapp.dtos.responses.ApiResponse;
import com.africa.semicolon.diaryapp.exceptions.DiaryAppException;
import com.africa.semicolon.diaryapp.services.DiaryService;
import com.africa.semicolon.diaryapp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v2/diaryapp/diaries")
@Slf4j
public class DiaryController {
    private DiaryService diaryService;
    private UserService userService;


    @Autowired
    public DiaryController(DiaryService diaryService, UserService userService) {
        this.diaryService = diaryService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/create/{userId}")
    private ResponseEntity<?> createDiary(@Valid @NotNull @NotBlank @PathVariable("userId") String userId, @NotNull @NotBlank @RequestParam String title){
        log.info("User Service --> {}", userService);
        try {
            User user = userService.findById(Long.valueOf(userId));
            Diary diary = diaryService.createDiary(title,user);
            Diary savedDiary = userService.addDiary(Long.valueOf(userId), diary);
            ApiResponse apiResponse = ApiResponse.builder()
                    .payload(savedDiary)
                    .isSuccessful(true)
                    .message("diary added successfully")
                    .statusCode(201)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        } catch (DiaryAppException e) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .message(e.getMessage())
                    .isSuccessful(false)
                    .statusCode(404)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        } );
        return errors;
    }
}
