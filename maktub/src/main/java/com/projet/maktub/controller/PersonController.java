package com.projet.maktub.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.maktub.model.Person;
import com.projet.maktub.model.Product;
import com.projet.maktub.repository.PersonRepository;
import com.projet.maktub.services.PersonService;



@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("persons")
public class PersonController {



	@Autowired 
	PersonService personService;
	
	@Autowired
	PersonRepository  personRepository;
    
	@GetMapping
	public ResponseEntity<List<Person>> getAllPersons(@RequestParam(required = false) String nom) {
		try {
			List<Person> persons = new ArrayList<Person>();
				personRepository.findAll().forEach(persons::add);
				personRepository.findByNom(nom).forEach(persons::add);
			if (persons.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(persons, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/{idperson}")
	public ResponseEntity<Person> getPersonById(@PathVariable("idperson") Integer idperson) {
		Optional<Person> personData = personRepository.findById(idperson);

		if (personData.isPresent()) {
			return new ResponseEntity<>(personData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	@PostMapping("/create")
	public ResponseEntity<Person> create(@RequestParam("idperson")Integer idperson, @RequestParam("mail")String mail, @RequestParam("password")String password ) throws IOException {
		Person pers = new Person(idperson,mail,password);
		Person _person = personRepository.save(pers);
		return new ResponseEntity<>(_person, HttpStatus.CREATED);
	
	}
	
	
	
	
	
	@PostMapping(path="/new")
	public ResponseEntity<Person> create(@RequestBody Person utilisateur){
		
		try {
			this.personService.saveUser(utilisateur);
			return new ResponseEntity<Person>(utilisateur,HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		}
	}

	
	
	@PutMapping("/{idperson}")
	public ResponseEntity<Person> updatePerson(@RequestParam("idperson")Integer idperson, @RequestParam("mail")String mail, @RequestParam("password")String password ) throws IOException {
		Optional<Person> personData = personRepository.findById(idperson);

		if (personData.isPresent()) {
			Person _person = personData.get();
			_person.setIdperson(idperson);
			_person.setMail(mail);
			_person.setPassword(password);
			
			

			return new ResponseEntity<>(personRepository.save(_person), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@DeleteMapping("/{idperson}")
	public ResponseEntity<HttpStatus> deletePerson(@PathVariable("idperson") Integer idperson) {
		try {
	       personRepository.deleteById(idperson);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAllPersons() {
		try {
			personRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}



