package com.secprog.starter.controllers;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secprog.starter.exceptions.NotFoundException;
import com.secprog.starter.model.PhoneBookEntry;
import com.secprog.starter.services.IPhoneBookService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/PhoneBook")
public class PhoneBookController {

	public IPhoneBookService phoneBookService;
	private final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);
	
	public PhoneBookController(IPhoneBookService phoneBookService) {
		this.phoneBookService = phoneBookService;
	}


	@GetMapping("/list")
	public List<PhoneBookEntry> list() {
		return phoneBookService.list();
	}

	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody @Valid PhoneBookEntry newEntry, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
		try {
			phoneBookService.add(newEntry);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (Exception e) {
			logger.error("Error adding phone book entry", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PutMapping("/deleteByName")
	public ResponseEntity<Object> deleteByName(@RequestParam("name") String name){
		try {
			phoneBookService.deleteByName(name);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		catch (Exception e) {
			logger.error("Error deleting phone book entry by name", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/deleteByNumber")
	public ResponseEntity<Object> deleteByNumber(@RequestParam("phnNumber") String number){
		try {
			phoneBookService.deleteByNumber(number);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		catch (Exception e) {
			logger.error("Error deleting phone book entry by number", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
