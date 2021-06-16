package com.auth.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.auth.exception.UserAlreadyExistException;
import com.auth.exception.UserNotFoundException;
import com.auth.model.UserData;
import com.auth.repo.UserRepo;
import com.auth.service.UserService;

import io.jsonwebtoken.JwtException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@WebMvcTest

public class UserControllerTest {
	
	UserData user;
	UserRepo repo;
	
	@Autowired
	MockMvc mockmvc;
	
	@InjectMocks
	UserController mycontrol;
	
	@MockBean
	UserService userservice;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		mockmvc=MockMvcBuilders.standaloneSetup(mycontrol).build();
		
		user=new UserData();
		user.setUsername("savi");
		user.setMailid("savi@mail.com");
		user.setPassword("savi123");
		user.setPhoneno("1234567890");
		user.setCountry("India");
		
		}
	
	@Test
	public void addUsersuccessTest() throws  Exception
	{

		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/authenticate/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(converttojson(user)))
				.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
				
				
		
	}
	
	
	@Test
    public void testLoginUser() throws Exception {


        String mailid = "savi@mail.com";
        String password = "savi123";


        Mockito.when( userservice.findUserdata(mailid, password)).thenReturn(user);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/authenticate/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isAccepted()).andDo(MockMvcResultHandlers.print());
    }

	

//	 @Test
//	    public void updateUserSuccess() throws Exception {
//	        user.setPassword("23456789");
//	        when(userservice.updateUser((user.getMailid()).thenReturn(user);
//	        mockmvc.perform(put("/api/authenticate/updateuser")
//	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//	                .andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
//
//	    }

	
	 @Test
	    public void addUserFailure() throws Exception {

	        when(userservice.addUser(any())).thenThrow(UserAlreadyExistException.class);
	        mockmvc.perform(post("/api/authenticate/addUser")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
	                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

	    }

//	 @Test
//	    public void updateUserFailure() throws Exception {
//	            user.setPhoneno("23456789");
//	            when(userservice.findUserdata(user.getMailid(),user.getPassword()).thenreturn(user);
//	            mockmvc.perform(put("/api/authenticate/updateuser/savi@gmail.com")
//	                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//	                    .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//
//	    }

	 


	private String converttojson(Object obj) throws JsonProcessingException
	{
		
		 ObjectMapper mapobj=new ObjectMapper();
		 String jsonoutput=mapobj.writeValueAsString(obj);
		 
		 return jsonoutput;
		 
	}
	
	 private static String jsonToString(final Object obj) throws JsonProcessingException {
	        String result;
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            final String jsonContent = mapper.writeValueAsString(obj);
	            result = jsonContent;
	        } catch (JsonProcessingException e) {
	            result = "Json processing error";
	        }
	        return result;
    }
	 
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }



}

	



