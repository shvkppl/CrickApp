package com.auth.service;

import com.auth.exception.UserAlreadyExistException;
import com.auth.exception.UserNotFoundException;
import com.auth.model.UserData;

public interface UserService {
	
	UserData addUser(UserData user) throws UserAlreadyExistException;
	
	UserData findUserdata(String mailid,String pwd);
	
	public UserData updateUserdata(UserData user ) throws UserNotFoundException;


}
