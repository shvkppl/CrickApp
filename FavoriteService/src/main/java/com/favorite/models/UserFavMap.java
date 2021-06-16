package com.favorite.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserFavMap {
	
	@Id
	private String mailId;
	private List<Long> favorites;
	
	public UserFavMap() {
		
	}
	
	public UserFavMap(String mailid) {
		this.mailId = mailid;
		this.favorites = new ArrayList<Long>();
	}
	
	public String getMailId() {
		return mailId;
	}
	
	public void setMailId(String mailid) {
		this.mailId = mailid;
	}
	public List<Long> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<Long> favorites) {
		this.favorites = favorites;
	}
	
	

}
