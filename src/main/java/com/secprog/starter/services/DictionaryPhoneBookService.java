package com.secprog.starter.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secprog.starter.exceptions.NotFoundException;
import com.secprog.starter.model.PhoneBookEntry;


@Service
public class DictionaryPhoneBookService implements IPhoneBookService {
	private final HashMap<String, String> phoneBookEntries;



    public DictionaryPhoneBookService() {
        phoneBookEntries = new HashMap<>();
    }

	@Override
	public void add(PhoneBookEntry phoneBookEntry) {
		// TODO Auto-generated method stub
		if (phoneBookEntry.getName().isEmpty()  || phoneBookEntry.getPhoneNumber().isEmpty()) {
			throw new IllegalArgumentException("Name and phone number must both be specified.");
        }
		
        phoneBookEntries.put(phoneBookEntry.getName(), phoneBookEntry.getPhoneNumber());

	}

	@Override
	public List<PhoneBookEntry> list() {
        List<PhoneBookEntry> entriesList = new ArrayList<>();
        
        for (String name : phoneBookEntries.keySet()) {
            entriesList.add(new PhoneBookEntry(name, phoneBookEntries.get(name)));
        }

        return entriesList;
    }
	@Override
	public void deleteByName(String name) throws NotFoundException {	
			if (!phoneBookEntries.containsKey(name)) {
	            throw new NotFoundException("No phonebook entry found containing name " + name + ".");
	        }

		phoneBookEntries.remove(name);

	}
	@Override
	public void deleteByNumber(String number) throws NotFoundException {
		// TODO Auto-generated method stub
		String name = null;
            for (String key : phoneBookEntries.keySet()) {
                if (phoneBookEntries.get(key).equals(number)) {
                    name = key;
                    break;
                }
            }
        	if (name == null) {
                throw new NotFoundException("No phonebook entry found containing phone number " + number + ".");
            }
        phoneBookEntries.remove(name);
		
	}

}
