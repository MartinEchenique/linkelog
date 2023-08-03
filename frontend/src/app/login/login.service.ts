import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { HttpClient } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import { SignUpDetails } from '../Dto/SignUpDetails.dto';

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
  login(userId:number):Subscription{
    return this.http.get(`http://localhost:8080/user/${userId}`).subscribe((data:any) =>{
      this.loggedUser = data;
    });
  }
  signUp(bodyInfo:SignUpDetails):Observable<any>{
    return this.http.post(`http://localhost:8080/user/new`,bodyInfo,{observe: 'response'});
  }
  getLoggedUser(){
    return this.loggedUser;
  }
}
