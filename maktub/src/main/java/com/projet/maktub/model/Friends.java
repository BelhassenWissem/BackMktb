package com.projet.maktub.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Friends")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friends {
	
	@Id
	private Integer idfriend;
	
/*
	@JsonIgnore
	@ManyToMany(mappedBy="friends")
	private List<Person> persons = new ArrayList<>();
*/
}
