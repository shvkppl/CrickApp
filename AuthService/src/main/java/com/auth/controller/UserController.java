package com.auth.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.exception.UserAlreadyExistException;
import com.auth.exception.UserNotFoundException;
import com.auth.model.UserData;
import com.auth.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin
@RestController
@RequestMapping("/api/authenticate")
public class UserController {

	
	@Autowired
	UserService userservice;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> adduser(@RequestBody UserData userdata)
	{
		
		System.out.println(userdata);
		try {
			return new ResponseEntity<UserData>(userservice.addUser(userdata),HttpStatus.OK);		
		}
		catch(UserAlreadyExistException excep) {
			return new ResponseEntity<String>(excep.getMessage(), HttpStatus.CONFLICT);		
		}
		
	}

//	@GetMapping("/getUser/{mailid}")
//	public ResponseEntity<?> getuser(@PathVariable String mailid)
//	{
//		
//		System.out.println(mailid);
//		try {
//			return new ResponseEntity<UserData>(userservice.addUser(userdata),HttpStatus.OK);		
//		}
//		catch(UserAlreadyExistException excep) {
//			return new ResponseEntity<String>(excep.getMessage(), HttpStatus.CONFLICT);		
//		}
//		
//	}
	
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody UserData userdata)
	{
	

UserData userfound=	userservice.findUserdata(userdata.getMailid(),userdata.getPassword());

if (userfound==null)
{
	return new ResponseEntity<String>("Invalid user/password",HttpStatus.UNAUTHORIZED);
}

else
{
	String token=generateMyToken(userfound);
	HashMap map=new HashMap();
	map.put("mytoken", token);
	return new ResponseEntity<HashMap>(map,HttpStatus.ACCEPTED);
}
}
	
	
private String generateMyToken(UserData userdata)
{
	
	long expirytime=10_000_000;
	
	return Jwts.builder().setSubject(userdata.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+expirytime))
				.signWith(SignatureAlgorithm.HS256, "ibmkey")
				.compact();
				
	
}


@PutMapping("/updateuser")
//@ApiImplicitParams({
//    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
//                      required = true, dataType = "string", paramType = "header") })
public ResponseEntity<?> updateUserdata(@RequestBody UserData userdata)
{
	
	System.out.println("\n\n\n\n" + userdata.getMailid() + "\n\n\n\n");
	
try {
	UserData userupdated=userservice.updateUserdata(userdata);
	return new ResponseEntity<UserData>(userupdated,HttpStatus.ACCEPTED);
}

catch (UserNotFoundException e) {
	
	System.out.println("exception");
	 return new ResponseEntity<String>("Userdata object not found" , HttpStatus.NOT_FOUND);
	
}
	
}


}
