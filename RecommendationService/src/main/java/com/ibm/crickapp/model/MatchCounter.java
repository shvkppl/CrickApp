package com.ibm.crickapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MatchCounter {
	@Id
	private long matchId;
	Match match;
	int counter=0;
	
	public MatchCounter(Match match, int counter) {
		
		this.match = match;
		this.counter = counter;
	}
	public MatchCounter() {
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public long getMatchId() {
		return matchId;
	}
	public void setMatchId(long l) {
		this.matchId = l;
	}
	

}
