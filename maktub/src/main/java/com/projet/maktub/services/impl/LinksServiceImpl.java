package com.projet.maktub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.maktub.model.Links;
import com.projet.maktub.model.OrderClient;
import com.projet.maktub.model.Person;
import com.projet.maktub.model.Product;
import com.projet.maktub.repository.PersonRepository;
import com.projet.maktub.services.LinksService;

@Service
public class LinksServiceImpl implements LinksService {


	@Autowired
	PersonRepository personRepository;
	
	
	
	@Override
	public boolean addLinkToUser(Person person,  String name , String url) {
		Person persond;
		persond =  this.personRepository.findByMail(person.getMail()).get();
		 Links linkDone;
		 linkDone = new Links(persond, name , url);
		 if(persond!=null) {
				if(persond.getLinks().add(linkDone)) {
					this.personRepository.save(persond);
					return true;
				}
				}
				return false;
	}

}
