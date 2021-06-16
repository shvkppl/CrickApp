import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { AuthenticationService } from '../services/authentication.service';
import { LoginEventService } from '../services/login-event.service';
import { RouterService } from '../services/router.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  myimage: string = "assets/image/registration.jpg";

  mailid: FormControl;
  password: FormControl;
  bearertoken: string = "";
  submitmessage: string = "";
  msg: string = "";
  message: string = "";
  action: string = "";
  @Output() loggedIn = new EventEmitter<string>();
  constructor(private authservice: AuthenticationService, private routeserve: RouterService, private loginEmitter: LoginEventService, private _snackbar: MatSnackBar) {

    this.mailid = new FormControl('', Validators.required);
    this.password = new FormControl('', Validators.minLength(3));
  }

  ngOnInit(): void {
  }

  loginData() {
    let data = {
      mailid: this.mailid.value,
      password: this.password.value
    }

    if (this.mailid.valid) {
      this.authservice.authenticateUser(data).subscribe
        (
          (Response: any) => {
            this.bearertoken = Response['mytoken'];
            this.authservice.setbearerToken(this.bearertoken);
            // this.authservice.setUser(JSON.parse(this.authservice.urlBase64Decode(this.bearertoken.split('.')[1]))['sub']);
            this.loginEmitter.emit(JSON.parse(this.authservice.urlBase64Decode(this.bearertoken.split('.')[1]))['sub']);
            // console.log(JSON.parse(this.authservice.urlBase64Decode(this.bearertoken.split('.')[1])))
            console.log(this.mailid.value);
            this.authservice.setMailId(this.mailid.value);
            this.loggedIn.emit(sessionStorage.getItem('user'));
            this._snackbar.open("Welcome " + sessionStorage.getItem('mailid').split('@')[0]);
            this.routeserve.routeToHome();
          },
          err => {
            if (err.status === 403) {
              this.submitmessage = err.error.message;
              console.log('forbidden client');
            } else {
              this.submitmessage = "Invalid credentials";
            }
          }
        );
    }
  }



  checkUsermail(): string {
    if (this.mailid.touched && this.mailid.invalid) {
      return "Email ID cant be Null";
    }
    else
      return "";
  }
}

