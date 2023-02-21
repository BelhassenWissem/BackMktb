package com.projet.maktub.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.projet.maktub.model.OrderClient;
import com.projet.maktub.model.Person;
import com.projet.maktub.model.Product;
import com.projet.maktub.repository.PersonRepository;
import com.projet.maktub.services.OrderClientService;


import org.springframework.stereotype.Service;

@Service
public class OrderClientServiceImpl implements OrderClientService{
	
	
	
	@Autowired
	PersonRepository personRepository;
	
	
	@Override
	public boolean addProductDoneToUser(Product product, Person person,  String code , int qte) {
		Person persond;
		persond =  this.personRepository.findByMail(person.getMail()).get();
		 OrderClient productDone;
		 productDone = new OrderClient(product, persond, code , qte);
		 if(persond!=null) {
				if(persond.getOrderClient().add(productDone)) {
					this.personRepository.save(persond);
					return true;
				}
				}
				return false;
	}

	
	@Override
	public boolean removeProductDoneFromUser(Product productToRemove, Person person) {
	
			OrderClient productDone = new OrderClient(productToRemove, person , "" , 0 );
			person.getOrderClient().remove(productDone);
				this.personRepository.save(person);
				return true;
			
		
	}

	
	

	@Override
	public List<OrderClient> getAlluserDoneProducts(String mail) {
		Person person;
		person =  this.personRepository.findByMail(mail).get();
		 List<OrderClient> liste = new ArrayList<>();
		 if(person!=null) {
			liste = person.getOrderClient();

					}
		return liste;
	}

}
