import { Component, OnInit } from '@angular/core';
import { Match } from '../models/match';
import { MatchesService } from '../services/matches.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  match: Match;
  matches: Array<Match> = [];
  favPresent: boolean = true;
  q: any;
  errorMsg: string;
  constructor(private matchesService: MatchesService, private route: RouterService) {
    this.match = new Match();
  }


  ngOnInit(): void {
    // this.getUpcomingMatches();
    // this.matchesService.fetchMatchesFromServer()
    this.favPresent = true;
    this.getMatches();



  }

  getMatches() {
    console.log("inside matches");
    this.matchesService.fetchMatchesFromServer().subscribe(
      matches => {
        this.matches = matches.filter(match => match.favorited)
        if (this.matches.length === 0) {
          this.favPresent = false;
        }

      },
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


}
