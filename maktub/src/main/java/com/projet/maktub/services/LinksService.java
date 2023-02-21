package com.projet.maktub.services;

import com.projet.maktub.model.Person;

public interface LinksService {

	boolean addLinkToUser(Person person,  String name , String url);

}
