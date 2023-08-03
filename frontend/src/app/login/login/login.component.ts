import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username:string ="user";
  password:string="pass";

  onLogIn(){
    console.log("username: " + this.username)
  }
}
