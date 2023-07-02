import { User } from "./user.model";

export class Comentario {
  autor:User;
  postId:number;
  texto:string;
  fechaPublicacion:Date;
  constructor(autor:User, texto:string, postId:number){
    this.autor = autor;
    this.texto = texto;
    this.postId = postId;
    this.fechaPublicacion = new Date();
  }
}