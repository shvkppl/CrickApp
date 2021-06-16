package com.ibm.crickapp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Match {
	@Id
	 long unique_id;
	 String date; 
//     String dateTimeGMT;
//	 @JsonProperty("team-1")
     String team_1;
//	 @JsonProperty("team-2")
     String team_2;
	Boolean squad;
     String toss_winner_team;
    // String winner_team;
     Boolean matchStarted;
    // String type;
     
     public Match() {
 	
 	}
	public Match(long unique_id, String date, String team_1, String team_2, Boolean squad, String toss_winner_team,
			Boolean matchStarted) {
		this.unique_id = unique_id;
		this.date = date;
		this.team_1 = team_1;
		this.team_2 = team_2;
		this.squad = squad;
		this.toss_winner_team = toss_winner_team;
		this.matchStarted = matchStarted;
	}
	public long getUnique_id() {
		return unique_id;
	}
	public void setUnique_id(long unique_id) {
		this.unique_id = unique_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTeam_1() {
		return team_1;
	}
	public void setTeam_1(String team_1) {
		this.team_1 = team_1;
	}
	public String getTeam_2() {
		return team_2;
	}
	public void setTeam_2(String team_2) {
		this.team_2 = team_2;
	}
	public Boolean getSquad() {
		return squad;
	}
	public void setSquad(Boolean squad) {
		this.squad = squad;
	}
	public String getToss_winner_team() {
		return toss_winner_team;
	}
	public void setToss_winner_team(String toss_winner_team) {
		this.toss_winner_team = toss_winner_team;
	}
	public Boolean getMatchStarted() {
		return matchStarted;
	}
	public void setMatchStarted(Boolean matchStarted) {
		this.matchStarted = matchStarted;
	}
     
}