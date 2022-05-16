package com.africa.semicolon.diaryapp;

import com.africa.semicolon.diaryapp.datas.models.User;
import com.africa.semicolon.diaryapp.datas.repositories.UserRepository;
import com.africa.semicolon.diaryapp.dtos.requests.UserRequest;
import com.africa.semicolon.diaryapp.exceptions.DiaryAppException;
import com.africa.semicolon.diaryapp.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceMockTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();
    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @Test
    void userCanCreateAccountTest() throws DiaryAppException {
        UserRequest userRequest = UserRequest.builder().email("test@email.com").password("test").build();
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(new User());
        userService.createUser(userRequest);
        verify(userRepository, times(1)).findUserByEmail(userRequest.getEmail());
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        assertThat(user.getEmail()).isEqualTo(userRequest.getEmail());
        assertThat(user.getPassword()).isEqualTo(userRequest.getPassword());
    }

}
