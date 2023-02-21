package com.projet.maktub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.projet.maktub.model.Person;
import com.projet.maktub.model.Product;
import com.projet.maktub.services.OrderClientService;
import com.projet.maktub.services.PersonService;
import com.projet.maktub.services.ProductService;


@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("productsdone")

public class OrderClientController {
	
	
	@Autowired
	OrderClientService orderClientService;
	
	@Autowired 
	PersonService utilisateurService;
	@Autowired
	ProductService produitService;

	
	@PostMapping(path="/addProductDoneToUser")
	public ResponseEntity<Product> addBookDoneToUser(@RequestBody ObjectNode json){
		Person person = new Person();
		Product productToAdd = new Product();
		try {
			person = new ObjectMapper().treeToValue(json.get("person"), Person.class);
			productToAdd = new ObjectMapper().treeToValue(json.get("product"), Product.class);
			int qte = new ObjectMapper().treeToValue(json.get("qte"), Integer.class);
			String code = new ObjectMapper().treeToValue(json.get("code"), String.class);

			boolean test = this.orderClientService.addProductDoneToUser(productToAdd,person,code , qte);
			if(test)
			return new ResponseEntity<Product>(productToAdd,HttpStatus.OK);

		} catch (JsonProcessingException e) {
			System.out.println("Parsing Exception!!");
			e.printStackTrace();
			return new ResponseEntity<Product>(HttpStatus.NOT_ACCEPTABLE);

		}
		return new ResponseEntity<Product>(HttpStatus.NOT_ACCEPTABLE);

	}
	

}
