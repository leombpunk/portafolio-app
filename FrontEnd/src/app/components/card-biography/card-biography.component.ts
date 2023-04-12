import { Component, OnInit, Input } from '@angular/core';
import { faLinkedin, faGithub } from '@fortawesome/free-brands-svg-icons';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { Biography } from '../../models/biography';

@Component({
  selector: 'app-card-biography',
  templateUrl: './card-biography.component.html',
  styleUrls: ['./card-biography.component.css']
})
export class CardBiographyComponent implements OnInit {
  
  faLinkedin = faLinkedin;
  faGithub = faGithub;
  faEnvelope = faEnvelope;

  mailto: string = "mailto:";

  @Input() githubLink: string = "";
  @Input() linkedinLink: string = "";
  @Input() imageURL: string = "";
  @Input() titleBio: string = "";
  @Input() descripcionBio: string = "";
  @Input() perfil_id: number = 0;
  @Input() email: string = "";
  @Input() altImagen: string = "";

  //variable que pasa los datos del perfil al boton para cargar el modal correspodiente
  @Input() pasero: Biography = new Biography();
  @Input() isLogged: boolean = false;

  constructor() { }

  ngOnInit(): void {
    // console.log("card id: "+this.perfil_id);
  }

}
