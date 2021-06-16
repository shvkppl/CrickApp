package com.auth.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.auth.exception.UserAlreadyExistException;
import com.auth.exception.UserNotFoundException;
import com.auth.model.UserData;
import com.auth.repo.UserRepo;

public class UserServiceTest {

	@InjectMocks
    UserServiceImpl userservice;
	
	
	@Mock
	UserRepo userrepo;
	
	
	UserData user;

	private UserData optional;
	
	@Before
	public void initialize()
	{
		MockitoAnnotations.initMocks(this);
		user=new UserData();
		user.setUsername("savi");
		user.setMailid("savi@mail.com");
		user.setPassword("savi123");
		user.setPhoneno("1234567890");
		user.setCountry("India");
	}
	
	@Test
    public void saveUserSuccess() throws UserAlreadyExistException
    {
    	Mockito.when(userrepo.save(user)).thenReturn(user);
      	UserData userres = userservice.addUser(user);
         Assert.assertEquals(userres, user);
    
    }
	
	
	 @Test
	    public void testSaveUserFailure() throws UserAlreadyExistException {

	        Mockito.when(userrepo.findByMailidAndPassword("savi@mail.com", "savi123")).thenReturn(optional);
	        Mockito.when(userrepo.save(user)).thenReturn(user);
	        UserData flag = userservice.addUser(user);
	        Assert.assertEquals("Cannot Register User", user, flag);

	    }


	 @Test
	    public void testFindByMailidAndPassword() throws UserNotFoundException {
	        Mockito.when(userrepo.findByMailidAndPassword("savi@mail.com", "savi123")).thenReturn(user);
	        UserData fetchedUser = userrepo.findByMailidAndPassword("savi@mail.com", "savi123");
	        Assert.assertEquals("savi@mail.com", fetchedUser.getMailid());
	    }


	

}
