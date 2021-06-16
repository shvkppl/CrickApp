package com.favorite.models;

public class FavToBeRemovedOrAdded {

	private String mailId;
	private Long matchId;
	
	public FavToBeRemovedOrAdded(String mailId, Long matchId) {
		this.mailId = mailId;
		this.matchId = matchId;
	}
	
	public FavToBeRemovedOrAdded() {
		
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailid) {
		this.mailId = mailid;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchid) {
		this.matchId = matchid;
	}
	
	
	
}
