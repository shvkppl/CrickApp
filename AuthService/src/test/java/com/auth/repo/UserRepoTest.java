package com.auth.repo;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth.model.UserData;
import com.auth.repo.UserRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)

public class UserRepoTest {
	
	@Autowired
	UserRepo repo;
	
	UserData user;
	
	@Before
	public void inti()
	{
		user=new UserData();
		user.setUsername("savi");
		user.setMailid("savi@mail.com");
		user.setPassword("savi123");
		user.setPhoneno("1234567890");
		user.setCountry("India");
	}
	
	@Test
	public void adduserDataSuccess()
	{
		
	UserData saveuser=repo.save(user);
		
		Optional<UserData> user=Optional.ofNullable(repo.findByMailidAndPassword("savi@mail.com","savi123"));
		
		
	      UserData studresult=user.get();
		
		Assert.assertEquals(studresult.getUsername(),"savi");
	}

	 @Test
	    public void testLoginUserSuccess() {
	        repo.save(user);
	        UserData object = repo.findById(user.getMailid()).get();
	        Assert.assertEquals(user.getMailid(), object.getMailid());
	    }



}
