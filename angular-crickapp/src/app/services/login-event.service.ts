import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LoginEventService {


  // user:string;
  private loginEvent: BehaviorSubject<{ user: string, loggedIn: boolean }>;
  constructor(private authservice: AuthenticationService) {
    // this.user = '';
    if (sessionStorage.getItem('mytoken') !== null) {
      this.loginEvent = new BehaviorSubject<{ user: string, loggedIn: boolean }>(
        {
          user: JSON.parse(this.authservice.urlBase64Decode(sessionStorage.getItem('mytoken').split('.')[1]))['sub'],
          loggedIn: true
        }
      );
    }

    else {
      this.loginEvent = new BehaviorSubject<{ user: string, loggedIn: boolean }>({ user: '', loggedIn: false });
    }

  }

  listen() {
    return this.loginEvent;
  }
  emit(user: string) {

    this.loginEvent.next({ user: user, loggedIn: true });
  }

}
