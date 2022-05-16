package com.africa.semicolon.diaryapp.datas.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Email
    @Column(unique = true)
    private String email;
    @Size(min=6)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<Diary> diaries;

    @Override
    public String toString() {
        return String.format("id:%d\temail:%s", id, email);
    }

    public void addDiary(Diary diary){
        diaries.add(diary);
    }

}
