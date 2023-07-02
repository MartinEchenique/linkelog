import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from '../login.service';
import { Comentario } from '../models/comentario.model';
import { Post } from '../models/post.model';
import { ObtenerPostService } from '../post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit{
  @Input() postId:string;
  @Input() showComments:boolean;
  @Input() post:Post;
  commentText:string = "";
  constructor(private obtenerPostServicio:ObtenerPostService, private route:ActivatedRoute, private loginService:LoginService){

  }
  ngOnInit(): void {
   if (!this.post){
    if(!this.postId) this.postId = this.getPostIdFromUrl() || "";
    this.obtenerPostServicio.obtenerPostCompleto(this.postId).subscribe((data:any) => {
      let postData:Post = data;
      this.post = postData;
    })
    }  
  }
  getPostIdFromUrl() {
    return this.route.snapshot.paramMap.get('id') ;

  }
  toggleShowComments(){
    this.showComments = !this.showComments;
  }
  isLogged(){
    return this.loginService.isLogged();
  }
  getLoggedProfilePictureUrl(){
    let loggedUser = this.loginService.getLoggedUser();
    return loggedUser.profilePictureUrl;
  }
  loadComments(){
    this.obtenerPostServicio.obtenerComments(this.post.id).subscribe((data:any)=>{
      this.post.comentarios = data;
      this.showComments = true;
    });
  }
  addCommentToPost(){
    let  commentToAdd = new Comentario(this.loginService.getLoggedUser(), this.commentText, +this.post.id);
    this.obtenerPostServicio.addComment(commentToAdd).subscribe(()=> {
      this.loadComments();
    });
    
    this.commentText = ""
  }
}
