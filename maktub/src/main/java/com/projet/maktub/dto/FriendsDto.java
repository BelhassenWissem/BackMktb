package com.projet.maktub.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projet.maktub.model.Friends;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FriendsDto {
	
	
	 private Integer id;
	
	  @JsonIgnore
	  private List<PersonDto> persons;

	  

	  
	  public static FriendsDto fromEntity(Friends friends) {
	    if (friends == null) {
	      return null;
	    }

	    return FriendsDto.builder()
	        .id(friends.getIdfriend())
	        .build();
	      
	  }

	  public static Friends toEntity(FriendsDto dto) {
	    if (dto == null) {
	      return null;
	    }

	    Friends friends = new Friends();
	    friends.setIdfriend(dto.getId());
	  


	    return friends;
	  }
}
