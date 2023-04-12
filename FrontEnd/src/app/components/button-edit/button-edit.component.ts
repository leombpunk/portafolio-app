import { Component, OnInit, Input } from '@angular/core';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalAddEditBiographyComponent } from '../modal-add-edit-biography/modal-add-edit-biography.component';
import { ModalAddEditAcademicComponent } from '../modal-add-edit-academic/modal-add-edit-academic.component';
import { ModalAddEditExperienceComponent } from '../modal-add-edit-experience/modal-add-edit-experience.component';
import { ModalAddEditProjectComponent } from '../modal-add-edit-project/modal-add-edit-project.component';
import { ModalAddEditSkillComponent } from '../modal-add-edit-skill/modal-add-edit-skill.component';
import { ModalEditImageComponent } from '../modal-edit-image/modal-edit-image.component';
import { Biography } from '../../models/biography';
import { Experience } from 'src/app/models/experience';
import { Academics } from '../../models/academic';
import { Project } from 'src/app/models/projects';
import { Skill } from 'src/app/models/skills';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-button-edit',
  templateUrl: './button-edit.component.html',
  styleUrls: ['./button-edit.component.css']
})
export class ButtonEditComponent implements OnInit {

  faPenToSquare = faPenToSquare;

  @Input() modalTarget: string = '';
  @Input() type: string = '';
  @Input() perfil_id: number = 0;

  //esta variable recibira un objeto de tipo Biography para
  //cargar los datos que contiene en el modal de editar perfil/biografia
  @Input() perfilData: Biography = new Biography();
  @Input() expeData: Experience = new Experience();
  @Input() academData: Academics = new Academics();
  @Input() projectData: Project = new Project();
  @Input() skillData: Skill = new Skill();

  constructor(private modalService: NgbModal, private toastr: ToastrService) {}

  ngOnInit(): void {}

  openModal() {
    //evaluar el parametro si es 1 o 2 por ejemplo
    //si es 1 cargar los modales de editar los datos
    //si es 2 cargar los modales de editar imagenes
    const modal = {
      bio:
        this.type === '1'
          ? ModalAddEditBiographyComponent
          : ModalEditImageComponent,
      experience:
        this.type === '1'
          ? ModalAddEditExperienceComponent
          : ModalEditImageComponent,
      academic:
        this.type === '1'
          ? ModalAddEditAcademicComponent
          : ModalEditImageComponent,
      project:
        this.type === '1'
          ? ModalAddEditProjectComponent
          : ModalEditImageComponent,
      skill:
        this.type === '1' ? ModalAddEditSkillComponent : ModalEditImageComponent
    };

    switch (this.modalTarget) {
      case 'biography':
        let wea = this.modalService.open(modal.bio, {
          backdrop: 'static',
          centered: true
        });
        wea.componentInstance.titleModal = 'Editar Perfil';
        if (this.type === '1') {
          //testeo de seteo en formulario
          wea.componentInstance.formBiography.setValue({
            id: this.perfilData.id,
            titulo: this.perfilData.titulo,
            nombre: this.perfilData.nombre,
            apellido: this.perfilData.apellido,
            acercade: this.perfilData.acercade,
            correo: this.perfilData.correo,
            linkedin: this.perfilData.linkedin,
            github: this.perfilData.github,
            usuarios_id: this.perfilData.usuarios_id,
          });
          wea.componentInstance.bio = this.perfilData;
        } else {
          //cargar modal con imagen
          // console.log("perfildata: "+this.perfilData.id);
          wea.componentInstance.id = this.perfilData.id;
          wea.componentInstance.whatEdit = this.modalTarget;
          wea.componentInstance.formBiographyImg.setValue({
            id: this.perfilData.id,
            img: ''
          });
        }
        break;
      case 'skill':
        let wea2 = this.modalService.open(modal.skill, {
          backdrop: 'static',
          centered: true
        });
        wea2.componentInstance.titleModal = 'Editar Habilidad';
        if (this.type === '1') {
          wea2.componentInstance.skill = this.skillData;
          wea2.componentInstance.formSkill.setValue({
            id: this.skillData.id,
            descripcion: this.skillData.descripcion,
            nivel: this.skillData.nivel,
            tipo_habilidad_id: this.skillData.tipo_habilidad_id,
            usuarios_id: this.skillData.usuarios_id
          });
        } else {
          // console.log("no existe foto para editar");
          this.toastr.error(
            'La seccion Habilidades no posee imagen que editar',
            'Error!',
            {
              timeOut: 3000,
              positionClass: 'toast-bottom-right'
            }
          );
        }
        break;
      case 'project':
        let wea3 = this.modalService.open(modal.project, {
          backdrop: 'static',
          centered: true
        });
        wea3.componentInstance.titleModal = 'Editar Proyecto';
        if (this.type === '1') {
          wea3.componentInstance.project = this.projectData;
          wea3.componentInstance.formProject.setValue({
            id: this.projectData.id,
            nombre: this.projectData.nombre,
            descripcion: this.projectData.descripcion,
            desde: this.projectData.desde,
            hasta: this.projectData.hasta,
            sitio: this.projectData.sitio,
            enlace: this.projectData.enlace,
            usuarios_id: this.projectData.usuarios_id
          });
        } else {
          // console.log("boton editar proyecto: (folo/logo)");
          // console.log(this.projectData);
          wea3.componentInstance.id = this.projectData.id;
          wea3.componentInstance.whatEdit = this.modalTarget;
          wea3.componentInstance.formBiographyImg.setValue({
            id: this.projectData.id,
            img: ''
          });
        }
        break;
      case 'academic':
        let wea4 = this.modalService.open(modal.academic, {
          backdrop: 'static',
          centered: true
        });
        wea4.componentInstance.titleModal = 'Editar Educación';
        if (this.type === '1') {
          // console.log("boton editar educacion: (datos-object)");
          // console.log(this.academData);
          let armoCadena: string = this.academData.habilidades.toString();
          // this.academData.habilidades.forEach((element: any) => {
          //   armoCadena += element;
          // });
          // console.log("armoCadena: " + armoCadena);
          wea4.componentInstance.academ = this.academData;
          wea4.componentInstance.formAcademic.setValue({
            id: this.academData.id,
            titulo: this.academData.titulo,
            institucion: this.academData.institucion,
            locacion: this.academData.locacion,
            habilidades: armoCadena,
            desde: this.academData.desde,
            hasta: this.academData.hasta,
            usuarios_id: this.academData.usuarios_id
          });
        } else {
          // console.log("boton editar educacion: (folo/logo)");
          // console.log(this.academData);
          wea4.componentInstance.id = this.academData.id;
          wea4.componentInstance.whatEdit = this.modalTarget;
          wea4.componentInstance.formBiographyImg.setValue({
            id: this.academData.id,
            img: ''
          });
        }
        break;
      case 'experience':
        let wea5 = this.modalService.open(modal.experience, {
          backdrop: 'static',
          centered: true
        });
        wea5.componentInstance.titleModal = 'Editar Experiencia Laboral';
        if (this.type === '1') {
          let armoCadena: string = this.expeData.tarea.toString();
          // console.log("boton editar experiencia (datos)");
          // console.log(this.expeData);
          wea5.componentInstance.expe = this.expeData;
          wea5.componentInstance.formExperience.setValue({
            id: this.expeData.id,
            usuarios_id: this.expeData.usuarios_id,
            cargo: this.expeData.cargo,
            empresa: this.expeData.empresa,
            desde: this.expeData.desde,
            hasta: this.expeData.hasta,
            reftelef: this.expeData.reftelef,
            refnombre: this.expeData.refnombre,
            tarea: armoCadena
          });
        } else {
          // console.log("boton editar experiencia (folo/logo)");
          // console.log(this.expeData);
          wea5.componentInstance.id = this.expeData.id;
          wea5.componentInstance.whatEdit = this.modalTarget;
          wea5.componentInstance.formBiographyImg.setValue({
            id: this.expeData.id,
            img: ''
          });
        }
        break;
      default:
        // console.log('que forro que sos!');
        this.toastr.warning(
          'Opción no valida!',
          'Atención!',
          {
            timeOut: 3000,
            positionClass: 'toast-bottom-right'
          }
        );
        break;
    }
  }
}
