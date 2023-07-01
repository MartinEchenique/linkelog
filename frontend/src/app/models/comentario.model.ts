import { User } from "./user.model";

export class Comentario {
  autor:User;
  texto:string;
  fechaPublicacion:Date;
  constructor(autor:User, texto:string){
    this.autor = autor;
    this.texto = texto;
    this.fechaPublicacion = new Date();
  }
}