import { Comentario } from "./comentario.model";
import { Reaccion } from "./reaccion.model";
import { User } from "./user.model";

export class Post{
   autor:User;
   comentarios:Comentario[];
   fechaPublicacion:Date;
   reacciones:Reaccion[];
   id:string;
   text:String;
   img:String;

   constructor(id:string, autor:User, fechaPublicacion:Date, comentarios:Comentario[],reacciones:Reaccion[], text?:string, img?:string){
    this.id = id;
    this.autor = autor;
    this.comentarios = comentarios;
    this.reacciones = reacciones;
    this.fechaPublicacion = new Date;
    this.text = text ? text : "";
    this.img = img ? img : "";
   }
   addComment(comment:Comentario){
      this.comentarios.push(comment);
   }
   setComments(comentarios:Comentario[]){
      this.comentarios = comentarios;
   }   
}