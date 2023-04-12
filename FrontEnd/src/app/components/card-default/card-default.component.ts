import { Component, OnInit, Input } from '@angular/core';
import { Experience } from '../../models/experience';
import { Academics } from '../../models/academic';

@Component({
  selector: 'app-card-default',
  templateUrl: './card-default.component.html',
  styleUrls: ['./card-default.component.css']
})
export class CardDefaultComponent implements OnInit {

  @Input() imageURL: string = "";
  @Input() altImagen: string = "";
  // @Input() altCard: string = "";//eliminar y cambiar por altImagen donde haga falta

  @Input() apuntar: string = "";
  @Input() type1: string = "1";
  @Input() type2: string = "2";

  // atributos en comun
  @Input() titleCard: string = "";
  @Input() descripcionCard: string = "";
  @Input() locacionCard: string = ""; 
  @Input() desde: string = "";
  @Input() hasta: string = "";
  @Input() tareas: string[] = [];

  //atributos particulares de experiencia laboral
  @Input() refe_per: string = "";
  @Input() refe_tel: string = "";

  @Input() dataEnter: Experience = new Experience();
  @Input() dataEnter2: Academics = new Academics();
  @Input() isLogged: boolean = false;

  constructor() { }
  
  ngOnInit(): void { }
}
