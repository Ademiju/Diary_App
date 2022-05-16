package com.africa.semicolon.diaryapp.datas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String text;

    @CreationTimestamp
    private LocalDateTime entryTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @ManyToOne
    @JoinColumn(name="diary_id")
    private Diary diary;

    public Entry(String text) {
        this.text = text;
    }

}
