package com.ibm.crickapp.service;

import java.util.List;

import com.ibm.crickapp.model.Match;
import com.ibm.crickapp.model.MatchCounter;

public interface RecommendationService {
	Match addMatchAndIncrementCounter(Match match) ;
	List<MatchCounter> viewallMatcheswithCounter();
	boolean decrementCounter(Match matchnew);

}
