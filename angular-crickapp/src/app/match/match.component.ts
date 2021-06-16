import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Match } from '../models/match';
import { Score } from '../models/score';
import { RecserveService } from '../recserve.service';
import { FavoritesService } from '../services/favorites.service';
import { MatchesService } from '../services/matches.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchComponent implements OnInit {

  @Input()
  match: Match;

  // score: Score;
  score: string;
  scores: string[];
  isFlipped: boolean = false;
  infoAvailable: boolean = false;
  constructor(private route: RouterService, private matchesService: MatchesService, private recService: RecserveService, private _snackbar: MatSnackBar) {
    // this.score = new Score();
    // if (typeof this.score.score === undefined)
    //   this.score.description = "Info not available";
    // console.log("inside match component");
    // console.log(this.match);
  }
  ngOnInit(): void { if (typeof this.match.winner_team === 'undefined') this.match.winner_team = '' }

  toggleFav() {

    if (sessionStorage.getItem('mytoken') === null) {
      this.route.routeToLogin();
      this._snackbar.open("Login Required");
    }

    else {
      console.log(sessionStorage.getItem('mytoken'));
      if (!this.match.favorited) {
        this.matchesService.addFavToServer(this.match.unique_id)
          .subscribe(
            res => {
              // this.match.favorited = true
              this._snackbar.open("Added to Favorites");
            },
            err => {
              console.log(err.message)
              this._snackbar.open("Session Expired");
              sessionStorage.clear();
              this.route.routeToLogin();
            }
          );
        this.recService.addtoRecomendation(this.match)
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
        this.recService.removeFromRecomendation(this.match);
      }
    }
  }
  getScore() {
    this.isFlipped = true;
    this.matchesService.fetchScoreFromServer(this.match.unique_id).subscribe(
      (res) => {
        if (res.description !== undefined) {
          this.scores = res.description.split(' v ').map(str => str.replace('&amp;', '&'));
          if (this.scores.length == 2) {
            this.infoAvailable = true;
          }

        }
        else
          this.scores = ["Info not available"];

      },
      (err) => {
        this.scores = [err.message];
      }
    );
  }
  goBack() {
    this.isFlipped = false;
  }
}
