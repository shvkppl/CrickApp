import { Component } from '@angular/core';
import { MatchesService } from './services/matches.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cricAppSPA';
  constructor(private matchesService: MatchesService) {
    // this.matchesService.fetchUpcomingMatchesFromServer();
    this.matchesService.fetchMatchesFromServer();

  }

  // OnUserLogged(user: string) {
  //   console.log('event hadner', user);
  //   this.loggedUser = user;
  // }

}
