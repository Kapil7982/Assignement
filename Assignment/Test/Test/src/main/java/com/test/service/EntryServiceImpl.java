package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Entry;
import com.test.exception.EntryException;
import com.test.repo.EntryRepo;

@Service
public class EntryServiceImpl implements EntryService{
	
	@Autowired
	EntryRepo entryRepo;

	@Override
	public Entry saveNewEntry(Entry entry) throws EntryException {
		
		Entry newEntry = entryRepo.save(entry);
		
		return newEntry;
	}

}
