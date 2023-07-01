import { Component } from '@angular/core';
import { LoginService } from './login.service';
import { Post } from './models/post.model';
import { User } from './models/user.model';
import { ObtenerPostService } from './post.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'linkelog';
  constructor(private loginService:LoginService){
   
  };
  onInit(){

  }
  isLogged(){
    return this.loginService.isLogged();
  }

}
