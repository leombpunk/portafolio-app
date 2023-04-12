import { Component, OnInit, Input } from '@angular/core';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalDeleteSomeComponent } from '../modal-delete-some/modal-delete-some.component';
import { Biography } from '../../models/biography';
import { Experience } from '../../models/experience';
import { Academics } from '../../models/academic';
import { Project } from 'src/app/models/projects';
import { Skill } from 'src/app/models/skills';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-button-delete',
  templateUrl: './button-delete.component.html',
  styleUrls: ['./button-delete.component.css']
})
export class ButtonDeleteComponent implements OnInit {
  faTrashAlt = faTrashAlt;

  @Input() modalTarget: string = '';
  @Input() type: string = '';
  @Input() bioData: Biography = new Biography();
  @Input() expeData: Experience = new Experience();
  @Input() academData: Academics = new Academics();
  @Input() projectData: Project = new Project();
  @Input() skillData: Skill = new Skill();

  constructor(private modalService: NgbModal, private toastr: ToastrService) {}

  ngOnInit(): void {}

  openModal() {
    //evaluar el parametro si es 1 o 2 por ejemplo
    //si es 1 cargar los modales de editar imagenes
    //si es 2 cargar los modales de editar los datos
    const modal = {
      bio: ModalDeleteSomeComponent,
      experience: ModalDeleteSomeComponent,
      academic: ModalDeleteSomeComponent,
      project: ModalDeleteSomeComponent,
      skill: ModalDeleteSomeComponent
    };

    console.log('modalTarget:' + this.modalTarget);
    switch (this.modalTarget) {
      case 'biography':
        let wea = this.modalService.open(modal.bio, {
          backdrop: 'static',
          centered: true
        });
        //.result.then(result => {}, reason => {});
        wea.componentInstance.descpCard =
          this.type === '2' ? 'la imagen del Perfil' : 'el Perfil';
        wea.componentInstance.idUser = this.bioData.usuarios_id;
        wea.componentInstance.idItem = this.bioData.id;
        wea.componentInstance.idTarget = this.type;
        wea.componentInstance.idModule = this.modalTarget;
        break;
      case 'skill':
        let wea2 = this.modalService.open(modal.skill, {
          backdrop: 'static',
          centered: true
        });
        // .result.then(result => {}, reason => {});
        wea2.componentInstance.descpCard =
          this.type === '2' ? 'la imagen de la Habilidad' : 'la Habilidad';
        wea2.componentInstance.idUser = this.skillData.usuarios_id;
        wea2.componentInstance.idItem = this.skillData.id;
        wea2.componentInstance.idTarget = this.type;
        wea2.componentInstance.idModule = this.modalTarget;
        break;
      case 'project':
        let wea3 = this.modalService.open(modal.project, {
          backdrop: 'static',
          centered: true
        });
        // .result.then(result => {}, reason => {});
        wea3.componentInstance.descpCard =
          this.type === '2' ? 'la imagen del Proyecto' : 'el Proyecto';
        wea3.componentInstance.idUser = this.projectData.usuarios_id;
        wea3.componentInstance.idItem = this.projectData.id;
        wea3.componentInstance.idTarget = this.type;
        wea3.componentInstance.idModule = this.modalTarget;
        break;
      case 'academic':
        let wea4 = this.modalService.open(modal.academic, {
          backdrop: 'static',
          centered: true
        });
        // .result.then(result => {}, reason => {});
        wea4.componentInstance.descpCard =
          this.type === '2'
            ? 'la imagen del Instituto Educativo'
            : 'los datos del Instituto Educativo';
        wea4.componentInstance.idUser = this.academData.usuarios_id;
        wea4.componentInstance.idItem = this.academData.id;
        wea4.componentInstance.idTarget = this.type;
        wea4.componentInstance.idModule = this.modalTarget;
        break;
      case 'experience':
        let wea5 = this.modalService.open(modal.experience, {
          backdrop: 'static',
          centered: true
        });
        // .result.then(result => {}, reason => {});
        // console.log("expeData: ");
        // console.log(this.expeData);
        wea5.componentInstance.descpCard =
          this.type === '2'
            ? 'la imagen de la Empresa'
            : 'los datos de la Empresa';
        wea5.componentInstance.idUser = this.expeData.usuarios_id;
        wea5.componentInstance.idItem = this.expeData.id;
        wea5.componentInstance.idTarget = this.type;
        wea5.componentInstance.idModule = this.modalTarget;
        break;
      default:
        // console.log('que forro que sos');
        this.toastr.warning('Opción no valida!', 'Atención!', {
          timeOut: 3000,
          positionClass: 'toast-bottom-right'
        });
        break;
    }
  }
}
