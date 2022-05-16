package com.africa.semicolon.diaryapp.datas.repositories;

import com.africa.semicolon.diaryapp.datas.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
