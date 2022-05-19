package com.africa.semicolon.diaryapp.services;

import com.africa.semicolon.diaryapp.datas.models.Diary;
import com.africa.semicolon.diaryapp.datas.models.Role;
import com.africa.semicolon.diaryapp.datas.models.User;
import com.africa.semicolon.diaryapp.datas.repositories.UserRepository;
import com.africa.semicolon.diaryapp.dtos.requests.UserRequest;
import com.africa.semicolon.diaryapp.dtos.responses.UserResponse;
import com.africa.semicolon.diaryapp.exceptions.DiaryAppException;
import com.africa.semicolon.diaryapp.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    private final ModelMapper mapper = new ModelMapper();
    @Override
    public UserResponse createUser(UserRequest userRequest) throws DiaryAppException {
        Optional<User> OptionalUser = userRepository.findUserByEmail(userRequest.getEmail());
        if(OptionalUser.isEmpty()){
            User user = new User(userRequest.getEmail(),userRequest.getPassword());
            User savedUser = userRepository.save(user);
            return mapper.map(savedUser, UserResponse.class);
        }
        throw new DiaryAppException("Email already exist");
    }
    @Override
    public Diary addDiary(@NotNull Long id, @NotNull Diary diary) throws DiaryAppException {
        User user = userRepository.findById(id).orElseThrow(()-> new DiaryAppException("user does not exist"));
        user.addDiary(diary);
        userRepository.save(user);
        return diary;
    }

    @Override
    public User findById(Long id) throws DiaryAppException {
        return userRepository.findById(id).orElseThrow(() -> new DiaryAppException("User does not exist"));
    }

    @Override
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
                .collect(Collectors.toSet());
    }
}
