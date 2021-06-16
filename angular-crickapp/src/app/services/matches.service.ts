import { UpcomingMatch } from '../models/upcomingMatch';
import { Match } from '../models/match';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { ResponseUpcomingMatches } from '../models/responseUpcomingMatches';
import { ResponseMatches } from '../models/responseMatches';
import { BehaviorSubject, Observable } from 'rxjs';
import { Score } from '../models/score';
import { FavoritesService } from './favorites.service';
import { UserFavMatches } from '../models/userFavMatches';
import { userFavToBeAddedOrRemoved } from '../models/userFavToBeAddedOrRemoved';

@Injectable({
  providedIn: 'root'
})
export class MatchesService {


  upcomingMatches: Array<UpcomingMatch>;
  upcomingMatchesSubject: BehaviorSubject<Array<UpcomingMatch>>;

  matches: Array<Match>;
  matchesSubject: BehaviorSubject<Array<Match>>;

  favMatches: Array<Match>;
  favMatchesSubject: BehaviorSubject<Array<Match>>;

  favToBeAddedOrRemoved: userFavToBeAddedOrRemoved;

  constructor(private httpclient: HttpClient, private favService: FavoritesService) {

    this.matches = new Array<Match>();
    this.matchesSubject = new BehaviorSubject([]);
    this.upcomingMatches = new Array<UpcomingMatch>();
    this.upcomingMatchesSubject = new BehaviorSubject([]);
    this.favMatches = new Array<Match>();
    this.favMatchesSubject = new BehaviorSubject([]);


  }

  fetchScoreFromServer(unique_id: number) {
    return this.httpclient.get<Score>(`https://cricapi.com/api/cricketScore/ojoiQhjPc1NmLLDnC4MkUA3JgiP2?unique_id=${unique_id}`
    );
  }


  fetchUpcomingMatchesFromServer() {
    const apikey = '';
    return this.httpclient.get<ResponseUpcomingMatches>('https://cricapi.com/api/matchCalendar?apikey=ojoiQhjPc1NmLLDnC4MkUA3JgiP2'
    ).subscribe((res) => {
      // console.log("inside subscription of upcoming matches");
      // console.log(res.data);
      this.upcomingMatches = res.data;
      this.upcomingMatchesSubject.next(this.upcomingMatches);
    },
      (err) => {
        this.upcomingMatchesSubject.error(err);
      }
    );

  }

  getUpcomingMatches(): BehaviorSubject<Array<UpcomingMatch>> {
    this.fetchUpcomingMatchesFromServer();
    return this.upcomingMatchesSubject;
  }

  fetchMatchesFromServer(): BehaviorSubject<Match[]> {
    const apikey = 'ojoiQhjPc1NmLLDnC4MkUA3JgiP2';

    this.httpclient.get<ResponseMatches>(`https://cricapi.com/api/matches?apikey=${apikey}`
    ).subscribe((resMatches) => {

      if (sessionStorage.getItem('mytoken') !== null) {
        console.log("getting inside without having token");
        this.fetchFavsFromServer()
          .subscribe(
            (resUserFavs) => {
              this.matches = resMatches.matches.map(match => {
                // console.log('inside fetch matches', match.unique_id);
                if (resUserFavs.favorites.includes(match.unique_id)) {
                  return Object.assign({}, match, {
                    team_1: match['team-1'], team_2: match['team-2'], favorited: true
                  })
                }
                else {
                  return Object.assign({}, match, {
                    team_1: match['team-1'], team_2: match['team-2'], favorited: false
                  })
                }
              });
              this.matchesSubject.next(this.matches);
            },
            (err) => { this.matchesSubject.error(err.message) }
          );
      }
      else {
        this.matches = resMatches.matches.map(
          (match) => {
            return Object.assign({}, match, {
              team_1: match['team-1'], team_2: match['team-2'], favorited: false
            });
          }
        );
        this.matchesSubject.next(this.matches);
      }
    },
      err => err.message);



    return this.matchesSubject;
  }


  getMatches(): BehaviorSubject<Array<Match>> {
    if (this.matches.length === 0)
      this.fetchMatchesFromServer();
    return this.matchesSubject;
  }


  fetchFavsFromServer()
    : Observable<UserFavMatches>
  // : BehaviorSubject<Match[]> 
  {
    // setTimeout(() => {

    // }, 1000);
    // console.log('this will be called after');
    return this.httpclient.get<UserFavMatches>(`http://localhost:8081/api/favorites/getFavs/${sessionStorage.getItem('mailid')}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
      }
    )






  }


  getFavMatches(): BehaviorSubject<Match[]> {
    return this.favMatchesSubject;
  }







  addFavToServer(matchId: number) {
    this.favToBeAddedOrRemoved = new userFavToBeAddedOrRemoved(sessionStorage.getItem('mailid'), matchId);
    console.log(this.favToBeAddedOrRemoved)
    // console.log(sessionStorage.getItem('mytoken'));
    // console.log(matchId);
    // console.log({ "mailId": sessionStorage.getItem('mailid'), "matchId": matchId });
    this.httpclient.post<UserFavMatches>(`http://localhost:8081/api/favorites/addToFav`, this.favToBeAddedOrRemoved,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
      }
    ).subscribe(res => {
      this.matches = this.matches.map(
        (match) => {
          if (match.unique_id === matchId)
            return Object.assign({}, match, { favorited: true })
          else
            return match
        }
      );
      this.matchesSubject.next(this.matches);

      // code for snackbar
    },
      (err) => {
        this.favMatchesSubject.error(err);
        sessionStorage.clear();
        // code for snackbar
      }
    );

    return this.favMatchesSubject;
  }

  removeFavFromServer(matchId: number) {
    this.httpclient.post<UserFavMatches>(`http://localhost:8081/api/favorites/removeFromFav`, { mailId: sessionStorage.getItem('mailid'), matchId: matchId },
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
      }
    ).subscribe(res => {
      this.matches = this.matches.map(
        (match) => {
          if (match.unique_id === matchId)
            return Object.assign({}, match, { favorited: false })
          else
            return match
        }
      );
      this.matchesSubject.next(this.matches);

      // code for snackbar
    },
      (err) => {
        // this.favMatchesSubject.error(err);
        // sessionStorage.clear();
        // code for snackbar
      }
    );
    return this.favMatchesSubject;
  }




}
