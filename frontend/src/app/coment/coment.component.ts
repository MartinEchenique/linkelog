import { Component, Input } from '@angular/core';
import { Comentario } from '../models/comentario.model';

@Component({
  selector: 'app-coment',
  templateUrl: './coment.component.html',
  styleUrls: ['./coment.component.css']
})
export class ComentComponent {
  @Input() comment:Comentario;
  constructor(){
  }
}
