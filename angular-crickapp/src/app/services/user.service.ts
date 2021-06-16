import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  readonly rootUrl = 'http://localhost:9096';
  constructor(private http: HttpClient) { }

  registerUser(user: User) {
    const body: User = {
      username: user.username,
      password: user.password,
      mailid: user.mailid,
      phoneno: user.phoneno,
      country: user.country
    }
    return this.http.post(this.rootUrl + '/api/authenticate/addUser', body);
  }

}
