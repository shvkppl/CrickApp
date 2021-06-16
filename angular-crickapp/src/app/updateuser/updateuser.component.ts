import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../models/user.model';
import { RouterService } from '../services/router.service';
import { UpdateserveService } from '../services/updateserve.service';

@Component({
  selector: 'app-updateuser',
  templateUrl: './updateuser.component.html',
  styleUrls: ['./updateuser.component.css']
})
export class UpdateuserComponent implements OnInit {

  user: User;
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";

  password: FormControl;
  username: FormControl;
  mailid: FormControl;
  phoneno: FormControl;
  country: FormControl;

  submitmessage: any;
  message: string = "WELCOME";
  action: string = "UserProfile Updated";

  constructor(private updateservice: UpdateserveService, private routeserve: RouterService, private _snackBar: MatSnackBar) {
    this.password = new FormControl('', Validators.minLength(6));
    this.username = new FormControl('', Validators.required);
    // this.mailid = new FormControl('', Validators.required);
    this.phoneno = new FormControl('', Validators.required);
    this.country = new FormControl('', Validators.required);
    this.user = new User();
    this.user.mailid = sessionStorage.getItem('mailid');

  }

  ngOnInit(): void {

  }



  OnSubmit() {

    let user = {
      mailid: sessionStorage.getItem('mailid'),
      password: this.password.value,
      username: this.username.value,
      country: this.country.value,

      phoneno: this.phoneno.value
    }

    this.updateservice.updateUser(this.user)
      .subscribe((data: any) => {

        console.log(data);
        this.routeserve.routeToHome();
        this._snackBar.open("Information Updated");

      }, err => {
        this.message = "ERROR"
        this.action = "Not Updated";
        this._snackBar.open(this.action);
      });
  }


}
