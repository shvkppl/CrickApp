import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  setUser(arg0: any) {
    sessionStorage.setItem('user', arg0);
  }


  urlBase64Decode(str: string) {
    let output = str.replace('-', '+').replace('_', '/');
    switch (output.length % 4) {
      case 0:
        break;
      case 2:
        output += '==';
        break;
      case 3:
        output += '=';
        break;
      default:
        throw 'Illegal base64url string!';
    }
    return window.atob(output);
  }

  setMailId(value: any) {
    sessionStorage.setItem("mailid", value);
  }

  getMailId(value: any) {
    sessionStorage.getItem("mailid");
  }

  url: string;
  constructor(private httpclient: HttpClient) {

    this.url = 'http://localhost:9096/api/authenticate/login';
  }

  authenticateUser(data: { mailid: string; password: string; }) {
    return this.httpclient.post(this.url, data);
  }

  setbearerToken(tok: string) {
    sessionStorage.setItem("mytoken", tok);
    // sessionStorage.setItem("user", JSON.)
  }

  getbearerToken() {
    return sessionStorage.getItem("mytoken");
  }

}

