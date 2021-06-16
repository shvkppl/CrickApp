package com.ibm.crickapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.crickapp.model.Match;
import com.ibm.crickapp.model.MatchCounter;
import com.ibm.crickapp.repository.RecommendationRepository;

@Service
public class RecommendationserviceImpl implements RecommendationService{
	@Autowired
	RecommendationRepository recRepo;
	@Override
	public Match addMatchAndIncrementCounter(Match match) {
		Optional<MatchCounter> matchfound=recRepo.findById(match.getUnique_id());
		//If match is present
		if(matchfound.isPresent())
		{	
			
		
			int count=matchfound.get().getCounter();
			count=count+1;
			MatchCounter matchCounter=new MatchCounter();
			matchCounter.setMatchId(match.getUnique_id());
			matchCounter.setMatch(match);
			matchCounter.setCounter(count);
			recRepo.save(matchCounter);
			System.out.println(matchCounter.getCounter());
			return matchCounter.getMatch();
		}
		

		else
		{
			MatchCounter matchCounter=new MatchCounter();
			matchCounter.setMatch(match);
			matchCounter.setCounter(1);
			matchCounter.setMatchId(match.getUnique_id());
			recRepo.save(matchCounter);
			return matchCounter.getMatch();

		}
		
	}
	@Override
	public List<MatchCounter> viewallMatcheswithCounter() {
		List<MatchCounter> myMatches=recRepo.findAll();
		return myMatches;
	}
	
	@Override
	public boolean decrementCounter(Match matchnew) {
		Optional<MatchCounter> matchfound=recRepo.findById(matchnew.getUnique_id());
		//If match is present decrement counter by 1.
		if(matchfound.isPresent()&& matchfound.get().getCounter()>1)
		{	
			int count=matchfound.get().getCounter();
			count=count-1;
			MatchCounter matchCounter=new MatchCounter();
			matchCounter.setMatchId(matchnew.getUnique_id());
			matchCounter.setMatch(matchnew);
			matchCounter.setCounter(count);
			recRepo.save(matchCounter);
			System.out.println(matchCounter.getCounter());
			return true;
		}
		else if(matchfound.isPresent()&& matchfound.get().getCounter()==1)
		{
			recRepo.deleteById(matchnew.getUnique_id());
			return true;

		}
		else
			return false;
		
	}
}