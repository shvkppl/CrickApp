import { Component, OnInit } from '@angular/core';
import { MatchesService } from '../services/matches.service';
import { UpcomingMatch } from '../models/upcomingMatch';

@Component({
  selector: 'app-match-calendar',
  templateUrl: './match-calendar.component.html',
  styleUrls: ['./match-calendar.component.css']
})
export class MatchCalendarComponent implements OnInit {

  upcomingMatch: UpcomingMatch;
  upcomingMatches: Array<UpcomingMatch> = [];
  q: any;

  // match: Match;
  // matches: Array<Match> = [];

  constructor(private matchesService: MatchesService) {
    this.upcomingMatch = new UpcomingMatch();
    // this.match = new Match();
  }


  ngOnInit(): void {
    this.getUpcomingMatches();
    // this.getMatches();

  }

  // getMatches() {
  //   console.log("inside matches");
  //   this.matchesService.getMatches().subscribe(matches => this.matches = matches);
  //   console.log("printing all the matches details")
  //   console.log(this.matches);
  // }

  getUpcomingMatches() {
    console.log("inside matches");
    this.matchesService.getUpcomingMatches().subscribe(upcomingMatches => this.upcomingMatches = upcomingMatches);
    console.log(this.upcomingMatches);
  }



}
