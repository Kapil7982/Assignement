package com.test.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.Entry;

@Repository
public interface EntryRepo extends JpaRepository<Entry, Integer>{

	public Optional<Entry> findByApi(String api);
}
