package com.auth.model;


import javax.persistence.Entity;
import javax.persistence.Id;


//import org.springframework.data.mongodb.core.mapping.Document;


@Entity
public class UserData {
	
	@Id
	String mailid;
	String phoneno;
	String country;
	
	String username;
	String password;
	
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	public String toString()
	{
		return "mailid " + mailid + " username " + username + "password " + password + "country" + country +"phoneno" + phoneno;
	}



}
