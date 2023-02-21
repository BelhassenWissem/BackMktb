package com.projet.maktub.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.maktub.dto.PersonDto;
import com.projet.maktub.model.Person;
import com.projet.maktub.repository.OrderClientRepository;
import com.projet.maktub.repository.PersonRepository;
import com.projet.maktub.services.PersonService;


@Service
public class PersonServiceImpl implements PersonService {

  private PersonRepository personRepository;
  private OrderClientRepository orderClientRepository;

  @Autowired
  public PersonServiceImpl(PersonRepository personRepository, OrderClientRepository orderClientRepository) {
    this.personRepository = personRepository;
    this.orderClientRepository = orderClientRepository;
  }

  @Override
  public PersonDto save(PersonDto dto) {
 
    return PersonDto.fromEntity(
    		personRepository.save(
    				PersonDto.toEntity(dto)
        )
    );
  }
  

	@Override
	public Optional<Person> findByEmail(String email) {
		return this.personRepository.findByMail(email);
	}

  @Override
  public PersonDto findById(Integer id) {
  
    return personRepository.findById(id)
        .map(PersonDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD")
        );
  }

  @Override
  public List<PersonDto> findAll() {
    return personRepository.findAll().stream()
        .map(PersonDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {

    personRepository.deleteById(id);
  }
  
  
  
	@Override
	public boolean saveUser(Person utilisateur) {
		utilisateur.setMail(utilisateur.getMail());
		utilisateur.setPassword(utilisateur.getPassword());

		if(this.personRepository.save(utilisateur)!=null) return true;
		else 
			return false;
	}
}
