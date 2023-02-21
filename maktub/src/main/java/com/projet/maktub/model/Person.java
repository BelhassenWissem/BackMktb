package com.projet.maktub.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Person")
public class Person {
	
	@Id
	@GeneratedValue
	private Integer idperson;

  @Column(name = "nom")
  private String nom;

  @Column(name = "prenom")
  private String prenom;


  @Column(name = "photo")
  private byte[] photo;

  @Column(name = "mail")
  private String mail;
  
  @Column(name = "password")
  private String password;

  @Column(name = "tel")
  private String tel;
  
  @Column(name = "adresse")
  private String adresse;
  
  
  

    public Person(Integer idperson, String mail, String password) {
	super();
	this.idperson = idperson;
	this.mail = mail;
	this.password = password;
}



/*
	@ManyToMany
	private List<Friends> friends;
*/
   
  
  
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Links> links = new ArrayList<Links>();
  
  
	 @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	    	@JsonIgnore
		    private List<OrderClient> orderClient = new ArrayList<OrderClient>();
  
  
  
  /*
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
  @JsonIgnore
  private List<Roles> roles;
*/
}
