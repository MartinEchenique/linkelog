import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Comentario } from '../models/comentario.model';
import { Post } from '../models/post.model';
import { Reaccion } from '../models/reaccion.model';
import { ObtenerPostService } from '../post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  posts:Post[];
  newPostText:string;
  constructor(private obtenerPostService:ObtenerPostService, private loginService:LoginService){}
  ngOnInit(): void {
    this.obtenerPostService.getPosts().subscribe((data:any) => {
      this.posts = data;
  
    });
  }
  addNewPost(){
    let id = "" + (this.obtenerPostService.generatedPosts.length + 2);
    let comments:Comentario[] = []
    let reactions:Reaccion[] = []
    this.obtenerPostService.addNewPost(new Post(id, this.loginService.getLoggedUser(),new Date(),comments, reactions, this.newPostText));
    this.newPostText = "";
    }
  isLogged(){
    return this.loginService.isLogged();
  }
  getLoggedProfilePictureUrl(){
    let loggedUser = this.loginService.getLoggedUser();
    return loggedUser.profilePictureUrl;
  }
}
