package com.africa.semicolon.diaryapp.controllers;

import com.africa.semicolon.diaryapp.dtos.responses.ApiResponse;
import com.africa.semicolon.diaryapp.dtos.requests.UserRequest;
import com.africa.semicolon.diaryapp.dtos.responses.UserResponse;
import com.africa.semicolon.diaryapp.exceptions.DiaryAppException;
import com.africa.semicolon.diaryapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v2/diaryapp")
public class UserController {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserController(UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostMapping("/user/create")
    public ResponseEntity<?>createUser(@RequestBody @Valid @NotNull @NotBlank UserRequest userRequest) throws DiaryAppException {

            String password = bCryptPasswordEncoder.encode(userRequest.getPassword());
            userRequest.setPassword(password);
            UserResponse userResponse = userService.createUser(userRequest);
            ApiResponse apiResponse = ApiResponse.builder().payload(userResponse).isSuccessful(true).statusCode(201).message("User created Successfully").build();
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
//        }catch (DiaryAppException error){
//            ApiResponse apiResponse = ApiResponse.builder().isSuccessful(false).statusCode(400).message(error.getMessage()).build();
//            return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
//        }
    }
}
