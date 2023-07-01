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
    this.generatedPosts.push(post);
  }
  generatedPosts:Post[];
  constructor(private http:HttpClient) { 
    /*
    let postList:Post[] = []; 
    let user = new User("Albert", "Einsten","","","https://thispersondoesnotexist.com/");
    let romeo = new User("Romeo", "Santos","","","https://thispersondoesnotexist.com/");
    let julieta = new User("Julieta", "Venegas","","","https://thispersondoesnotexist.com/");
    let comentarios:Comentario[] = [new Comentario(user, "Juli!! todo bien?"),new Comentario(romeo, "Todo perfecto! vos?"),new Comentario(julieta, "Yo todo bien!")];
    let emocion = new Emocion("like");
    let reacciones:Reaccion[] = [new Reaccion(user,emocion)];
    let post = new Post("001", julieta, new Date(),comentarios, reacciones, "Hola gente, como estan?");
    postList.push(post);

    let juan = new User("Juan", "Pablo","","","https://thispersondoesnotexist.com/");
    let cruz = new User("Cruz", "Ado","","","https://thispersondoesnotexist.com/");
    let abila = new User("Abila", "Unavez","","","https://thispersondoesnotexist.com/");
    let comentarios2:Comentario[] = [new Comentario(cruz, "no, como es?"),new Comentario(juan, "no dije no, como es? dije "),new Comentario(abila, "JAJA muy MALO!")];
    let emocion2 = new Emocion("like");
    let reacciones2:Reaccion[] = [new Reaccion(juan,emocion2)];
    let post2 = new Post("002", juan,new Date() ,comentarios2, reacciones2, "conocen el  cuento de la buena pipa?");
    postList.push(post2);
    this.generatedPosts = postList;*/
  }
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
  obtenerComments(commentId:string){
    let commentsResponse = this.http.get(`http://localhost:8080/comments/post/${commentId}`);
    return commentsResponse;
  }
  getPosts(){
   return this.http.get(`http://localhost:8080/allPosts`);
  }
  addComment(postId:string, comment:Comentario){
    this.http.post(`http://localhost:8080/comment/add`,comment)
    //let postToAddComment = this.generatedPosts.find((post)=>{return post.id === postId;})
    //if (postToAddComment){ postToAddComment.addComment(comment)}
  }
  
}
