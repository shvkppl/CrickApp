import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Match } from '../models/match';
import { MatchCounter } from '../models/matchCounter';
import { Score } from '../models/score';
import { RecserveService } from '../recserve.service';
import { FavoritesService } from '../services/favorites.service';
import { MatchesService } from '../services/matches.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.css']
})
export class TrendingComponent implements OnInit {

  @Input()
  matchcntr: MatchCounter;
  @Input()
  match: Match;
  @Input()
  counter: number;
  score: Score;
  constructor(private route: RouterService, private matchesService: MatchesService, private recServe: RecserveService, private _snackbar: MatSnackBar) {
    this.score = new Score();

    console.log("trending", this.match);
    console.log("trending", this.counter);
    // this.match = new Match();
    // this.counter = 0;
    // console.log("inside match component");
    // console.log(this.match);
  }
  ngOnInit(): void { if (typeof this.match.winner_team === 'undefined') this.match.winner_team = '' }

  toggleFav() {
    console.log("trending card", this.match);
    if (sessionStorage.getItem('mytoken') === null) {
      this.route.routeToLogin();
      this._snackbar.open("Login Required");
    }

    else {
      if (!this.match.favorited) {
        this.matchesService.addFavToServer(this.match.unique_id)
          .subscribe(
            res => {
              this._snackbar.open("Added to Favorites");
            },
            err => {
              this._snackbar.open("Session Expired");
              sessionStorage.clear();
              this.route.routeToLogin();
            }
          );
        this.recServe.addtoRecomendation(this.match)
      }
      else {
        this.matchesService.removeFavFromServer(this.match.unique_id)
          .subscribe(
            res => {
              this.match.favorited = false;
              this._snackbar.open("Removed from Favorites");
            },
            err => {
              this._snackbar.open("Session Expired");
              sessionStorage.clear();
              this.route.routeToLogin();
            }
          );
        console.log("calling remove from rec", this.match);
        this.recServe.removeFromRecomendation(this.match);
      }
    }
  }
  getScore() {
    this.matchesService.fetchScoreFromServer(this.match.unique_id).subscribe(
      (res) => {

        this.score = res;

      },
      (err) => {

      }
    );
  }

}
