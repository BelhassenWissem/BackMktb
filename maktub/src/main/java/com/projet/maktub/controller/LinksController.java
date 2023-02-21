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
import com.projet.maktub.model.Links;
import com.projet.maktub.model.Person;
import com.projet.maktub.model.Product;
import com.projet.maktub.services.LinksService;
import com.projet.maktub.services.PersonService;


@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("links")

public class LinksController {


	@Autowired 
	PersonService utilisateurService;
	

	@Autowired 
	LinksService linkService;
	
	
	@PostMapping(path="/addLinkToUser")
	public ResponseEntity<Links> addLinkToUser(@RequestBody ObjectNode json){
		Person person = new Person();
		Links linkToAdd = new Links();
		try {
			person = new ObjectMapper().treeToValue(json.get("person"), Person.class);
			String name = new ObjectMapper().treeToValue(json.get("name"), String.class);
			String url = new ObjectMapper().treeToValue(json.get("url"), String.class);

			boolean test = this.linkService.addLinkToUser(person, name , url);
			if(test)
			return new ResponseEntity<Links>(linkToAdd,HttpStatus.OK);

		} catch (JsonProcessingException e) {
			System.out.println("Parsing Exception!!");
			e.printStackTrace();
			return new ResponseEntity<Links>(HttpStatus.NOT_ACCEPTABLE);

		}
		return new ResponseEntity<Links>(HttpStatus.NOT_ACCEPTABLE);

	}
	
}
