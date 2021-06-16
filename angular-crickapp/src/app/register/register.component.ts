import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { User } from '../models/user.model';
import { RouterService } from '../services/router.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";

  password: FormControl;
  username: FormControl;
  mailid: FormControl;
  phoneno: FormControl;
  country: FormControl;

  submitmessage: any;
  message: string = "WELCOME";
  action: string = "User Registered";

  constructor(private userService: UserService, private routeserve: RouterService, private _snackBar: MatSnackBar) {
    this.password = new FormControl('', Validators.minLength(3));
    this.username = new FormControl('', Validators.required);
    this.mailid = new FormControl('', Validators.required);
    this.phoneno = new FormControl('', Validators.required);
    this.country = new FormControl('', Validators.required);



    this.user = new User();

  }

  ngOnInit(): void {

  }



  OnSubmit() {

    let user = {
      mailid: this.mailid.value,
      password: this.password.value,
      username: this.username.value,
      country: this.country.value,

      phoneno: this.phoneno.value
    }

    this.userService.registerUser(this.user)
      .subscribe((data: any) => {
        this._snackBar.open("Registration Successful");
        console.log(data);
        this.routeserve.routeToLogin();

      }, err => {
        this.message = "ERROR"
        this.action = "Not Registered";
        this._snackBar.open(this.action);
      });
  }

  // getVal(username) {
  //   alert(username + " is registered");
  // }

}