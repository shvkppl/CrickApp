import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
// import { receiveMessageOnPort } from 'node:worker_threads';
import { BehaviorSubject } from 'rxjs';
import { Match } from './models/match';
import { MatchCounter } from './models/matchCounter';
import { MatchesService } from './services/matches.service';

@Injectable({
  providedIn: 'root'
})
export class RecserveService {
  url: string = 'http://localhost:9062/api/recommendation/viewAll'
  matches: Match[];
  matchCntrSub: BehaviorSubject<MatchCounter[]>;
  matchCntrs: MatchCounter[];
  constructor(private httpcli: HttpClient, private matchesService: MatchesService) {
    this.matchCntrSub = new BehaviorSubject<MatchCounter[]>(this.matchCntrs);
    // this.matchesService.getMatches().subscribe(result => {
    //   this.matches = result;
    //   this.getallMatches();
    // });
    // this.matchCntrs = []
    // this.matchCntrSub = new BehaviorSubject<MatchCounter[]>(this.matchCntrs);
  }

  getTrendingMatches() {

    this.matchesService.getMatches().subscribe(result => {
      this.matches = result;
      this.getallMatches();
    });

    return this.matchCntrSub;
  }

  getallMatches() {

    this.httpcli.get<Array<MatchCounter>>(this.url, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
    }).subscribe(
      (res) => {
        console.log("printing result", res);
        if (res.length !== 0) {
          this.matchCntrs = res.map(matchcntr => {
            return Object.assign({}, matchcntr, { match: this.matches.find(match => match.unique_id === matchcntr.matchId) });
          });
          console.log("print match and likes", this.matchCntrs);
          this.matchCntrSub.next(this.matchCntrs);
        }
        else {
          this.matchCntrSub.next([]);
        }

      },
      (error) => {
        this.matchCntrSub.next([]);
      }
    );
    return this.matchCntrSub;
  }

  addtoRecomendation(match: Match) {
    this.httpcli.post('http://localhost:9062/api/recommendation/addmatch', match,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
      }
    ).subscribe(
      (res) => {
        // this.matchCntrs = this.matchCntrs.map(_matchcntr => {
        //   if (_matchcntr.matchId === matchcntr.matchId) {
        //     return Object.assign({}, _matchcntr, { counter: _matchcntr.counter + 1 });
        //   } else return _matchcntr;

        // });
        // this.matchCntrSub.next(this.matchCntrs);
        this.getallMatches();
      },
      error => {

        this.matchCntrSub.next(error.message)
      }
    );
  }

  removeFromRecomendation(match: Match) {
    console.log("this will be removed", match);
    this.httpcli.post('http://localhost:9062/api/recommendation/removematch', match,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
      }
    ).subscribe(
      (res) => {
        // this.matchCntrs = this.matchCntrs.map(_matchcntr => {
        //   if (_matchcntr.matchId === matchcntr.matchId) {
        //     return Object.assign({}, _matchcntr, { counter: _matchcntr.counter + 1 });
        //   } else return _matchcntr;

        // });
        // this.matchCntrSub.next(this.matchCntrs);
        this.getallMatches();
      },
      error => {
        console.log("error", error.message);
        // this.matchCntrSub.next(error.message)
        this.getallMatches();
      }
    );
  }



}
