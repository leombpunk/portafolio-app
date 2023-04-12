import { Component, OnInit, Input } from '@angular/core';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';

import { ModalAddEditAcademicComponent } from '../modal-add-edit-academic/modal-add-edit-academic.component';
import { ModalAddEditExperienceComponent } from '../modal-add-edit-experience/modal-add-edit-experience.component';
import { ModalAddEditProjectComponent } from '../modal-add-edit-project/modal-add-edit-project.component';
import { ModalAddEditSkillComponent } from '../modal-add-edit-skill/modal-add-edit-skill.component';

//prueba de servicio para pasarlo al modal (usuario_id)
import { WalkietalkieService } from '../../services/walkietalkie.service';


@Component({
  selector: 'app-button-add',
  templateUrl: './button-add.component.html',
  styleUrls: ['./button-add.component.css']
})
export class ButtonAddComponent implements OnInit {
  
  faPlus = faPlus;

  @Input() modalTarget: string = '';

  private usuario_id: number = 0;

  constructor(
    private modalService: NgbModal,
    private walkie: WalkietalkieService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.walkie.informarUsuId$.subscribe((result: any) => {
      // console.log("result "+result);
      this.usuario_id = result;
    });
  }

  openModal() {
    // console.log("modalTarget:"+this.modalTarget);
    switch (this.modalTarget) {
      case 'skill':
        let wea2 = this.modalService.open(ModalAddEditSkillComponent, {
          backdrop: 'static',
          centered: true
        });
        // .result.then(result => {}, reason => {});
        wea2.componentInstance.titleModal = 'Agregar Habilidad';
        wea2.componentInstance.usuario_id = this.usuario_id;
        break;
      case 'project':
        let wea3 = this.modalService.open(ModalAddEditProjectComponent, {
          backdrop: 'static',
          centered: true
        });
        // .result.then(result => {}, reason => {});
        wea3.componentInstance.titleModal = 'Agregar Proyecto';
        wea3.componentInstance.usuario_id = this.usuario_id;
        break;
      case 'academic':
        let wea4 = this.modalService.open(ModalAddEditAcademicComponent, {
          backdrop: 'static',
          centered: true
        });
        // .result.then(result => {}, reason => {});
        wea4.componentInstance.titleModal = 'Agregar Educación';
        wea4.componentInstance.usuario_id = this.usuario_id;
        break;
      case 'experience':
        let wea5 = this.modalService.open(ModalAddEditExperienceComponent, {
          backdrop: 'static',
          centered: true
        });
        // .result.then(result => {}, reason => {});
        wea5.componentInstance.titleModal = 'Agregar Experiencia Laboral';
        wea5.componentInstance.usuario_id = this.usuario_id;
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
