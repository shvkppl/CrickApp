package com.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.exception.UserAlreadyExistException;
import com.auth.exception.UserNotFoundException;
import com.auth.model.UserData;
import com.auth.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	UserRepo userrepo;
	
	@Override
	public UserData addUser(UserData user) throws UserAlreadyExistException {
		if(userrepo.findById(user.getMailid()).isPresent())
			throw new UserAlreadyExistException("User already exists");
		UserData userresult=userrepo.save(user);
		return userresult;
		
	}


	@Override
	public UserData findUserdata(String mailid, String pwd) {
		 
		
		UserData userfound=userrepo.findByMailidAndPassword(mailid, pwd);
		
		return userfound;

}
	
	@Override
	public UserData updateUserdata(UserData user) throws UserNotFoundException {
		 
		Optional<UserData> userexist=userrepo.findById(user.getMailid());
	
		if(userexist.isPresent())
		{
			UserData modifyuser=userexist.get();
			modifyuser.setPassword(user.getPassword().trim());
			modifyuser.setUsername(user.getUsername().toUpperCase());
			modifyuser.setPhoneno(user.getPhoneno());
		userrepo.save(modifyuser);
	
		}
		else
			throw new UserNotFoundException();
		
	 
		return user;
	}

}
