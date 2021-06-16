import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { AuthenticationService } from './authentication.service';


@Injectable({
  providedIn: 'root'
})
export class UpdateserveService {
  readonly rootUrl = 'http://localhost:9096';
  constructor(private http: HttpClient, private authservice: AuthenticationService) { }

  updateUser(user: User) {
    const body: User = {
      username: user.username,
      password: user.password,
      mailid: user.mailid,
      phoneno: user.phoneno,
      country: user.country
    }
    return this.http.put(this.rootUrl + '/api/authenticate/updateuser', body, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('mytoken')}`)
    });
  }

}
