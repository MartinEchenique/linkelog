import { Emocion } from "./emocion.model";
import { User } from "./user.model";

export class Reaccion {
  autor:User;
  emocion:Emocion;
  
  constructor(autor:User, emocion:Emocion){
    this.autor = autor;
    this.emocion = emocion;
  }
}