package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.test.entity.Data;
import com.test.entity.Entry;
import com.test.entity.OutputDto;
import com.test.exception.EntryException;
import com.test.service.EntryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class EntryController {
	
	@Autowired
	EntryService entryService;

	@Autowired
	RestTemplate restTempleate;
	
	@GetMapping("/entries/{category}")
	public List<OutputDto> entriesByCategoryHandler(@PathVariable("category") String category)
	{
		Data data = restTempleate.getForObject("https://api.publicapis.org/entries", Data.class);
		
		List<Entry> entries = data.getEntries();
		
		List<OutputDto> output = new ArrayList<>();
		
		List<OutputDto> list = new ArrayList<>();
		
		for(Entry entry : entries)
		{
			list.add(new OutputDto(entry.getApi(), entry.getDescription()));
		}
		
		return output;
	}
	
	@PostMapping("/entries")
	public ResponseEntity<String> savingNewEntries(@RequestBody Entry entry) throws EntryException
	{
		Data data = restTempleate.getForObject("https://api.publicapis.org/entries", Data.class);
		
		List<Entry> listOfEntries = data.getEntries();
		
		listOfEntries.add(entry);
		
		for(Entry entries : listOfEntries)
		{
			
			System.out.println(entries);
			Entry newEntry = entryService.saveNewEntry(entries);
		}
		
		return new ResponseEntity<String>("Entry added successfully", HttpStatus.CREATED);
	}
}
