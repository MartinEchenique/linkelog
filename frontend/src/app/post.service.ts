import { JsonPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Comentario } from './models/comentario.model';
import { Emocion } from './models/emocion.model';
import { Post } from './models/post.model';
import { Reaccion } from './models/reaccion.model';
import { User } from './models/user.model';

@Injectable({
  providedIn: 'root'
})
export class ObtenerPostService implements OnInit{

  addNewPost(post:Post) {
    this.http.post(`http://localhost:8080/post/add`,post).subscribe();
  }
  generatedPosts:Post[];
  constructor(private http:HttpClient) { }
  ngOnInit(): void {
  }

  obtenerPost(postId:string){
    let postResponse =  this.http.get(`http://localhost:8080/post/${postId}`);
    return postResponse;
  }
  obtenerPostCompleto(postId:string){
    return this.http.get(`http://localhost:8080/postComplete/${postId}`);
    }
  obtenerUser(userId:string){
    let userResponse = this.http.get(`http://localhost:8080/comments/post/${userId}`);
    return userResponse;
  }
  obtenerComments(postId:string){
    let commentsResponse = this.http.get(`http://localhost:8080/comments/post/${postId}`);
    return commentsResponse;
  }
  getPosts(){
   return this.http.get(`http://localhost:8080/allPosts`);
  }
  addComment(comment:Comentario){
   return this.http.post(`http://localhost:8080/add/comment`,comment)
  }
  
}
