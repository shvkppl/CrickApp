//package com.ibm.crickapp.repository;
//
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.ibm.crickapp.model.Match;
//import com.ibm.crickapp.model.MatchCounter;
//
//@RunWith(SpringRunner.class)
//@DataMongoTest
//public class RecommendationRepositoryTest {
//	@Autowired
//    private RecommendationRepository recRepo;
//
//    private Match match;
//    private MatchCounter matchcounter;
//    
//    @Before
//	public void initialize()
//	{
//         match = new Match();
//         match.setUnique_id(1252074);
//         match.setDate("2021-03-29T00:00:00.000Z");
//         match.setTeam_1("West Indies");
//         match.setTeam_2("Sri Lanka");
//         match.setSquad(true);
//         match.setToss_winner_team("Sri Lanka");
//         match.setMatchStarted(true);
//         
//         
//         matchcounter=new MatchCounter();
//         matchcounter.setMatchId(1252074);
//         matchcounter.setMatch(match);
//         matchcounter.setCounter(1);
//       
//		}
//    
////    @Test
////    public void saveMatchSuccess() 
////    {
////    	Mockito.when(recRepo.save(matchcounter)).thenReturn(matchcounter);
////    	Optional<Match> user=Optional.ofNullable(recRepo.());
////		
////		
////	      UserData studresult=user.get();
////		
////		Assert.assertEquals(studresult.getUsername(),"savi");
////
////    }
//    }
//
//
//
