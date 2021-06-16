import { Component, OnInit } from '@angular/core';
import { UpcomingMatch } from '../models/upcomingMatch';
import { MatchesService } from '../services/matches.service';
import { Match } from '../models/match';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-match-view',
  templateUrl: './match-view.component.html',
  styleUrls: ['./match-view.component.css']
})
export class MatchViewComponent implements OnInit {

  // upcomingMatch: UpcomingMatch;
  // upcomingMatches: Array<UpcomingMatch> = [];

  match: Match;
  matches: Array<Match> = [];

  q: any;
  errorMsg: string;
  constructor(private matchesService: MatchesService, private route: RouterService) {
    // this.upcomingMatch = new UpcomingMatch();
    this.match = new Match();
  }


  ngOnInit(): void {
    // this.getUpcomingMatches();
    // this.matchesService.fetchMatchesFromServer()
    this.getMatches();

  }

  getMatches() {
    console.log("inside matches");
    this.matchesService.fetchMatchesFromServer().subscribe(
      matches => this.matches = matches,
      err => {
        console.log(err);
        this.errorMsg = err;
        sessionStorage.clear();
        this.route.routeToLogin();
      }
    );
    console.log("printing all the matches details")
    console.log(this.matches);
  }

  // getUpcomingMatches() {
  //   console.log("inside matches");
  //   this.matchesService.getUpcomingMatches().subscribe(upcomingMatches => this.upcomingMatches = upcomingMatches);
  //   console.log(this.upcomingMatches);
  // }

}
