import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { LoginEventService } from '../services/login-event.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnChanges {


  user: string;
  loggedIn: boolean = false;
  constructor(private loginEventListener: LoginEventService, private route: RouterService) {
    this.user = '';
    this.loggedIn = false;
  }

  ngOnInit(): void {
    this.loggedIn = false;
    this.loginEventListener.listen().subscribe(user => {
      this.user = user.user;
      this.loggedIn = user.loggedIn;
    });
    console.log('loggedIn?:', this.loggedIn);
  }

  ngOnChanges(changes: SimpleChanges): void {
    // this.user=

  }
  logout() {
    this.loggedIn = false;
    this.user = '';
    sessionStorage.clear();

    this.route.routeToLogin();
  }

}
