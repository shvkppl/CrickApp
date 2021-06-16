import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';


@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(public router: Router, private location: Location) { }


  routeToLogin() {
    this.router.navigate(['login']);
  }

  routeToRegister() {
    this.router.navigate(['register']);
  }

  routeToHome() {
    this.router.navigate(['home']);
  }


}
