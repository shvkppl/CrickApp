package com.ibm.crickapp.controller;

import java.util.List;

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

import com.ibm.crickapp.model.Match;
import com.ibm.crickapp.model.MatchCounter;
import com.ibm.crickapp.repository.RecommendationRepository;
import com.ibm.crickapp.service.RecommendationService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RecommendationControllerTest {
	
    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private MatchCounter matchcounter;
   
    @MockBean
    private Match match;
  
    @MockBean
    private RecommendationService recService;
    
    @InjectMocks
    private RecommendationController recController;
    
    RecommendationRepository recRepo;
    private List<MatchCounter> matchList;
    
    @Before
	public void setup()
	{
    	  MockitoAnnotations.initMocks(this);
          mockmvc = MockMvcBuilders.standaloneSetup(recController).build();
          match = new Match();
          match.setUnique_id(1252074);
          match.setDate("2021-03-29T00:00:00.000Z");
          match.setTeam_1("West Indies");
          match.setTeam_2("Sri Lanka");
          match.setSquad(true);
          match.setToss_winner_team("Sri Lanka");
          match.setMatchStarted(true);
          
          
          matchcounter=new MatchCounter();
          matchcounter.setMatchId(1252074);
          matchcounter.setMatch(match);
          matchcounter.setCounter(2);
        
		}
    
    @Test
	public void addMatchSuccessTest() throws  Exception
	{

		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/recommendation/addmatch")
				.contentType(MediaType.APPLICATION_JSON)
				.content(converttojson(match)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
		.andDo(MockMvcResultHandlers.print());
				
				
		
	}
    
    @Test
 	public void removeMatchSuccessTest() throws  Exception
 	{
    	mockmvc.perform(MockMvcRequestBuilders.post("/api/recommendation/removematch")
 				.contentType(MediaType.APPLICATION_JSON)
 				.content(converttojson(match)))
 				.andExpect(MockMvcResultMatchers.status().isNotFound())
 		.andDo(MockMvcResultHandlers.print());
 	}
    
    @Test
    public void removeNoteFailure() throws Exception {


 		mockmvc.perform(MockMvcRequestBuilders.post("/api/recommendation/removematch")
 				.contentType(MediaType.APPLICATION_JSON)
 				.content(converttojson(match)))
 				.andExpect(MockMvcResultMatchers.status().isNotFound())
 		.andDo(MockMvcResultHandlers.print());
 				
 				
    }
    
    @Test
    public void getAllMatchSuccess() throws Exception {
       
        mockmvc.perform(MockMvcRequestBuilders.get("/api/recommendation/viewAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void getAllMatchFailure() throws Exception {
//       
//        mockmvc.perform(MockMvcRequestBuilders.get("/api/recommendation/viewAll")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
    

    private String converttojson(Object obj) throws JsonProcessingException
	{
		
		 ObjectMapper mapobj=new ObjectMapper();
		 String jsonoutput=mapobj.writeValueAsString(obj);
		 return jsonoutput;
		 
	}
}
