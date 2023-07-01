import { Injectable } from '@angular/core';
import { User } from './models/user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loggedUser:User;
  constructor() { }
  isLogged(){
    
    let isLogged = this.loggedUser ? true : false;
    return isLogged;
  }
  login(){
    this.loggedUser = new User("Albert", "Einsten","","","https://thispersondoesnotexist.com/");
  }
  getLoggedUser(){
    return this.loggedUser;
  }
}
