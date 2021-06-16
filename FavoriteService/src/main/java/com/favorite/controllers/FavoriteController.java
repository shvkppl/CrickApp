package com.favorite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favorite.models.FavToBeRemovedOrAdded;
import com.favorite.models.UserFavMap;
import com.favorite.services.FavoriteService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

	@Autowired
	FavoriteService favService;
	
	@PostMapping("/addToFav")
//	@ApiImplicitParams({
//	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
//	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<?> addToFav(@RequestBody FavToBeRemovedOrAdded favToBeAdded)
	{
		System.out.println("\n\n\n\n\n\n" + favToBeAdded.getMailId());
		System.out.println(favToBeAdded.getMatchId() + "\n\n\n\n\n\n\n");
		
			return new ResponseEntity<UserFavMap>(this.favService.addToFav(favToBeAdded), HttpStatus.CREATED);
		
//		return new ResponseEntity<String>("request failed", HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/removeFromFav")
//	@ApiImplicitParams({
//	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
//	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<?> removeFromFav(@RequestBody FavToBeRemovedOrAdded favToBeRemoved)
	{
			return new ResponseEntity<UserFavMap>(this.favService.removeFromFav(favToBeRemoved), HttpStatus.OK);
	
//		return new ResponseEntity<String>("request failed", HttpStatus.NOT_FOUND);
		
		
	}
	
	@GetMapping("/getFavs/{mailId}")
//	@ApiImplicitParams({
//	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
//	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<?> getFavs(@PathVariable String mailId)
	{
//		System.out.println("\n\n\n\n\n\n\n inside get fav controller\n\n\n\n\n\n");
		UserFavMap favs = this.favService.getFavs(mailId);
	
		return new ResponseEntity<UserFavMap>(favs, HttpStatus.OK);
	}
	
	
}
