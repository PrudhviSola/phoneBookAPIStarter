package com.secprog.starter.services;


import java.util.List;

import com.secprog.starter.exceptions.NotFoundException;
import com.secprog.starter.model.PhoneBookEntry;

public interface IPhoneBookService {
		//public void add(String name,String phoneNumber);
		public void add(PhoneBookEntry newEntry);
		public void deleteByName(String name) throws NotFoundException;
		public void deleteByNumber(String number) throws NotFoundException;
		public List<PhoneBookEntry> list();
}
