package com.ibm.crickapp.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.crickapp.model.Match;
import com.ibm.crickapp.model.MatchCounter;
import com.ibm.crickapp.service.RecommendationService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin
@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {
	@Autowired
	RecommendationService recService;

	public RecommendationController(RecommendationService recService) {
		this.recService = recService;
	}
	
	
	@PostMapping("/addmatch")
//	@ApiImplicitParams({
//	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
//	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<?> storeMatch(@RequestBody Match matchnew) 
	{
		
			Match response=recService.addMatchAndIncrementCounter(matchnew);
			return new ResponseEntity<Match> (response,HttpStatus.CREATED);
		
			
	}
	
	
	@PostMapping("/removematch")
//	@ApiImplicitParams({
//	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
//	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<?> removeMatch(@RequestBody Match matchnew) 
	{
			if(this.recService.decrementCounter(matchnew)) {
				return new ResponseEntity<String>("Successfully removed ", HttpStatus.OK);
			}
		
			else
			return new ResponseEntity<String>("Match not Exist", HttpStatus.NOT_FOUND);		
	}
	
	

	

	@GetMapping("/viewAll")
//	@ApiImplicitParams({
//	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
//	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<?> getallRecommendations()
	{
		List<MatchCounter> recMatches=recService.viewallMatcheswithCounter();
		Collections.sort(recMatches, new SortMatches());
		return new ResponseEntity<List<MatchCounter>>(recMatches,HttpStatus.OK);
		
	}
	
	class SortMatches implements Comparator<MatchCounter>
	{
	    // Used for sorting in descending order of counter
	    public int compare(MatchCounter a, MatchCounter b)
	    {
	        return (b.getCounter()>=a.getCounter()?1:-1);
	    }
	}

}

