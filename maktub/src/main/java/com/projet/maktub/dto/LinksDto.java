package com.projet.maktub.dto;

import com.projet.maktub.model.Links;
import lombok.Builder;
import lombok.Data;


	@Data
	@Builder
	public class LinksDto {
		
		
		 private Integer id;
		
		 private String name;
		 
		 private String url;
		

		  
		  public static LinksDto fromEntity(Links links) {
		    if (links == null) {
		      return null;
		    }

		    return LinksDto.builder()
		        .id(links.getIdlink())
		        .name(links.getName())
		        .url(links.getUrl())
		        .build();
		      
		  }

		  public static Links toEntity(LinksDto dto) {
		    if (dto == null) {
		      return null;
		    }

		    Links links = new Links();
		    links.setIdlink(dto.getId());
		    links.setName(dto.getName());
		    links.setUrl(dto.getUrl());
		  


		    return links;
		  }
	}

	
