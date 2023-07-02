import { Injectable } from '@angular/core';
import { User } from './models/user.model';
import { HttpClient } from '@angular/common/http';
import { Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loggedUser:User;
  constructor(private http:HttpClient) { }
  isLogged(){
    
    let isLogged = this.loggedUser ? true : false;
    return isLogged;
  }
  login():Subscription{
    return this.http.get(`http://localhost:8080/user/${1}`).subscribe((data:any) =>{
      this.loggedUser = data;
    });
   // this.loggedUser = new User("Albert", "Einsten","","","https://thispersondoesnotexist.com/");
  }
  getLoggedUser(){
    return this.loggedUser;
  }
}
