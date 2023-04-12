import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BiographyService } from '../../services/biography.service';
import { ExperienceService } from '../../services/experience.service';
import { AcademicService } from '../../services/academic.service';
import { WalkietalkieService } from '../../services/walkietalkie.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProjectService } from 'src/app/services/project.service';
import { SkillService } from 'src/app/services/skill.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-modal-delete-some',
  templateUrl: './modal-delete-some.component.html',
  styleUrls: ['./modal-delete-some.component.css']
})
export class ModalDeleteSomeComponent implements OnInit {
  @Input() descpCard: string = '';

  @Input() idUser: string = ''; //el usuario del cual quiero borrar algo
  @Input() idItem: string = ''; //el item especifico que quiero borrar, ej.: el id del registro que quiero borrar (en la tabla 'perfil' seria 'id')
  @Input() idModule: string = ''; //el modulo de donde quiero borrar (de que tabla en la base de datos) ej.: perfil, habilidades, educacion, experiencia, etc
  @Input() idTarget: string = ''; //quiero borrar solo la imagen o la entrada completa (1 para borrar los datos, 2 para borrar solo la imagen)

  @Output() onDeleteSome: EventEmitter<string> = new EventEmitter(); //mandar el numero o el nombre para saber que seccion recargar

  mErrMessage: string = '';
  mErrStatus: string = '';
  mErrStatusText: string = '';
  spinner: boolean = false;

  constructor(
    private modalActive: NgbActiveModal,
    private bioService: BiographyService,
    private expeService: ExperienceService,
    private acaService: AcademicService,
    private proService: ProjectService,
    private skillService: SkillService,
    private comunicationService: WalkietalkieService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {}

  deleteSome() {
    let response: any;
    console.log('idModule: ' + this.idModule);
    switch (this.idModule) {
      case 'biography':
        this.spinner = true;
        if (this.idTarget === '1') {
          //llamar al servicio para borrar el contenido especificado
          //en biografia no se borran los datos solo se actualizan
          // console.log('en biografia no se borran los datos solo se actualizan');
        }
        if (this.idTarget === '2') {
          //llamar al servicio para borrar el contenido especificado
          this.bioService.deleteBioImage(this.idItem).subscribe({
            next: (result: any) => {
              response = result;
              // console.log('response: ');
              // console.log(response);
              this.mErrMessage = '';
              this.toastr.success(
                'Foto del perfil borrada correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log('errorcito');
              // console.log(e);
              // console.log(e.ok);
              this.mErrMessage = e.error.mensaje || e.message;
              this.mErrStatus = e.status;
              this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar eliminar la foto.',
                'Error!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
              this.spinner = false;
            },
            complete: () => {
              this.comunicationService.actualizarBio(true);
              this.closeModal();
            }
          });
        }
        break;
      case 'academic':
        this.spinner = true;
        if (this.idTarget === '1') {
          //borrar datos
          this.acaService.deleteAcademic(this.idItem).subscribe({
            next: (result: any) => {
              response = result;
              // console.log('response (educacion): ');
              // console.log(response);
              this.mErrMessage = '';
              this.toastr.success(
                'Información borrada correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log('errorcito (educacion)');
              // console.log(e);
              this.mErrMessage = e.error.mensaje || e.message;
              this.mErrStatus = e.status;
              this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar eliminar la información.',
                'Error!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
              this.spinner = false;
            },
            complete: () => {
              this.comunicationService.actualizarAca(true);
              this.closeModal();
            }
          });
        }
        if (this.idTarget === '2') {
          //borrar imagen
          this.acaService.deleteAcademImage(this.idItem).subscribe({
            next: (result: any) => {
              response = result;
              // console.log('response (educacion imagen): ');
              // console.log(response);
              this.mErrMessage = '';
              this.toastr.success(
                'Logo del Instituto borrado correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log('errorcito (educacion)');
              // console.log(e);
              // console.log(e.ok);
              this.mErrMessage = e.error.mensaje || e.message;
              this.mErrStatus = e.status;
              this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar eliminar el logotipo.',
                'Error!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
              this.spinner = false;
            },
            complete: () => {
              this.comunicationService.actualizarAca(true);
              this.closeModal();
            }
          });
        }
        break;
      case 'skill':
        this.spinner = true;
        // console.log("idTarget: "+this.idTarget);
        if (this.idTarget === '1') {
          //borrar datos
          this.skillService.deleteSkill(this.idItem).subscribe({
            next: (result: any) => {
              response = result;
              // console.log('response (experiencia): ');
              // console.log(response);
              this.mErrMessage = '';
              this.toastr.success(
                'Información borrada correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log('errorcito (experiencia)');
              // console.log(e);
              // console.log(e.ok);
              this.mErrMessage = e.error.mensaje || e.message;
              this.mErrStatus = e.status;
              this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar eliminar los datos.',
                'Error!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
              this.spinner = false;
            },
            complete: () => {
              this.comunicationService.actualizarSkill(true);
              this.closeModal();
            }
          });
        }
        if (this.idTarget === '2') {
          // console.log("no hay imagen para borrar");
        }
        break;
      case 'experience':
        this.spinner = true;
        // console.log("idTarget: "+this.idTarget);
        if (this.idTarget === '1') {
          //borrar datos
          this.expeService.deleteExperience(this.idItem).subscribe({
            next: (result: any) => {
              response = result;
              // console.log('response (experiencia): ');
              // console.log(response);
              this.mErrMessage = '';
              this.toastr.success(
                'Información borrada correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log('errorcito (experiencia)');
              // console.log(e);
              // console.log(e.ok);
              this.mErrMessage = e.error.mensaje || e.message;
              this.mErrStatus = e.status;
              this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar eliminar los datos.',
                'Error!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
              this.spinner = false;
            },
            complete: () => {
              this.comunicationService.actualizarExpe(true);
              this.closeModal();
            }
          });
        }
        if (this.idTarget === '2') {
          //borrar imagen
          this.expeService.deleteExpeImage(this.idItem).subscribe({
            next: (result: any) => {
              response = result;
              // console.log('response (experiencia imagen): ');
              // console.log(response);
              this.mErrMessage = '';
              this.toastr.success(
                'Logo del trabajo borrado correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log('errorcito (experiencia)');
              // console.log(e);
              // console.log(e.ok);
              this.mErrMessage = e.error.mensaje || e.message;
              this.mErrStatus = e.status;
              this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar eliminar el logotipo.',
                'Error!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
              this.spinner = false;
            },
            complete: () => {
              this.comunicationService.actualizarExpe(true);
              this.closeModal();
            }
          });
        }
        break;
      case 'project':
        this.spinner = true;
        if (this.idTarget === '1') {
          //borrar datos
          this.proService.deleteProject(this.idItem).subscribe({
            next: (result: any) => {
              response = result;
              // console.log('response (proyecto): ');
              // console.log(response);
              this.mErrMessage = '';
              this.toastr.success(
                'Información borrada correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log('errorcito (proyecto)');
              // console.log(e);
              // console.log(e.ok);
              this.mErrMessage = e.error.mensaje || e.message;
              this.mErrStatus = e.status;
              this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar eliminar los datos.',
                'Error!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
              this.spinner = false;
            },
            complete: () => {
              this.comunicationService.actualizarProj(true);
              this.closeModal();
            }
          });
        }
        if (this.idTarget === '2') {
          //borrar imagen
          this.proService.deleteProjectImage(this.idItem).subscribe({
            next: (result: any) => {
              response = result;
              // console.log('response (proyecto imagen): ');
              // console.log(response);
              this.mErrMessage = '';
              this.toastr.success(
                'Logo del proyecto borrado correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log('errorcito (proyecto)');
              // console.log(e);
              // console.log(e.ok);
              this.mErrMessage = e.error.mensaje || e.message;
              this.mErrStatus = e.status;
              this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar eliminar el logotipo.',
                'Error!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
              this.spinner = false;
            },
            complete: () => {
              this.comunicationService.actualizarProj(true);
              this.closeModal();
            }
          });
        }
        break;
      default:
        // console.log('forro');
        this.toastr.info('Me quieres trollear?.', 'Atención!', {
          timeOut: 3000,
          positionClass: 'toast-bottom-right'
        });
        this.spinner = false;
        break;
    }
  }

  closeModal() {
    this.modalActive.close('Modal Closed');
  }

  dismissModal() {
    this.modalActive.dismiss('Cross click');
  }
}
