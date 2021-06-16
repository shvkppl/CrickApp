import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Match } from '../models/match';
import { UserFavMatches, UserFavMatches2 } from '../models/userFavMatches';
import { userFavToBeAddedOrRemoved } from '../models/userFavToBeAddedOrRemoved';
import { AuthenticationService } from './authentication.service';
import { MatchesService } from './matches.service';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {




  // userFavorites: Match[];
  // userFavSubject: BehaviorSubject<Match[]>;
  // matches: Match[] = [];


  // favtobeadded: userFavToBeAddedOrRemoved;

  // constructor(private httpclient: HttpClient,
  //   private matchesService: MatchesService,
  //   private authService: AuthenticationService) {

  //   this.matchesService.fetchMatchesFromServer();

  //   this.userFavorites = new Array<Match>();
  //   this.userFavSubject = new BehaviorSubject<Match[]>(this.userFavorites);
  //   this.matchesService.getMatches().subscribe(
  //     (res) => this.matches = res,
  //     (error) => console.log("some error occured")
  //   );

  //   // this.fetchFavsFromServer();

  // }


  // fetchFavsFromServer() {
  //   return this.httpclient.get<UserFavMatches>(`http://localhost:8081/api/favorites/getFavs/${sessionStorage.getItem('mailid')}`,
  //     {
  //       headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
  //     }
  //   ).subscribe(res => {
  //     this.userFavorites = this.matches.filter(
  //       match => res.favorites.includes(match.unique_id)
  //     );
  //     this.userFavSubject.next(this.userFavorites);
  //   },
  //     (err) => { this.userFavSubject.error(err); }
  //   );
  // }


  // addFavToServer(matchId: number) {
  //   this.favtobeadded = new userFavToBeAddedOrRemoved(sessionStorage.getItem('mailid'), matchId);
  //   // console.log(sessionStorage.getItem('mytoken'));
  //   // console.log(matchId);
  //   // console.log({ "mailId": sessionStorage.getItem('mailid'), "matchId": matchId });
  //   return this.httpclient.post<UserFavMatches>(`http://localhost:8081/api/favorites/addToFav`, this.favtobeadded,
  //     {
  //       headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
  //     }
  //   ).subscribe(res => {
  //     this.userFavorites = this.matches.filter(
  //       match => res.favorites.includes(match.unique_id)
  //     );
  //     this.userFavSubject.next(this.userFavorites);

  //     // code for snackbar
  //   },
  //     (err) => {
  //       this.userFavSubject.error(err);

  //       // code for snackbar
  //     }
  //   );
  // }


  // removeFavFromServer(matchId: number) {
  //   return this.httpclient.post<UserFavMatches>(`http://localhost:8081/api/favorites/removeFromFav`, { mailId: sessionStorage.getItem('mailid'), matchId: matchId },
  //     {
  //       headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
  //     }
  //   ).subscribe(res => {
  //     this.userFavorites = this.matches.filter(
  //       match => res.favorites.includes(match.unique_id)
  //     );
  //     this.userFavSubject.next(this.userFavorites);

  //     // code for snackbar
  //   },
  //     (err) => {
  //       this.userFavSubject.error(err);
  //       sessionStorage.clear();
  //       // code for snackbar
  //     }
  //   );
  // }



}
