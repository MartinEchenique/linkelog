export class Emocion {
  nombre:string;
  imagen:string;

  constructor(nombre:string, imagen?:string){
    this.nombre = nombre;
    this.imagen = imagen ? imagen : "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Facebook_Thumb_icon.svg/1200px-Facebook_Thumb_icon.svg.png";
  }
}