import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BiographyService } from '../../services/biography.service';
import { ExperienceService } from '../../services/experience.service';
import { AcademicService } from '../../services/academic.service';
import { WalkietalkieService } from '../../services/walkietalkie.service';
import { ProjectService } from 'src/app/services/project.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-modal-edit-image',
  templateUrl: './modal-edit-image.component.html',
  styleUrls: ['./modal-edit-image.component.css']
})
export class ModalEditImageComponent implements OnInit {
  @Input() formBiographyImg: FormGroup;
  @Input() titleModal: string = '';
  mErrImage: string = '';
  imgBandera: boolean = false;
  @Input() id: number = 0;
  @Input() usuario_id: string = '';
  filecito: any;
  whatEdit: string = ''; //para el switch, para saber que seccion va editar la foto/imagen/logo

  mErrMessage: string = '';
  mErrStatus: string = '';
  mErrStatusText: string = '';
  spinner: boolean = false;

  constructor(
    private modalActive: NgbActiveModal,
    private form: FormBuilder,
    private serviceBio: BiographyService,
    private serviceExpe: ExperienceService,
    private serviceAca: AcademicService,
    private servicePro: ProjectService,
    private comunicationService: WalkietalkieService,
    private toastr: ToastrService
  ) {
    this.formBiographyImg = this.form.group({
      id: [this.id, [Validators.required]],
      img: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {}

  public get Image() {
    return this.formBiographyImg.get('img');
  }
  public get ImageValid() {
    return this.Image!.touched && !this.Image!.valid;
  }
  public get ImageError() {
    // let id = this.formBiographyImg.get("id");
    // let img = this.Image ? this.Image.value : '';
    // console.log(this.Image);
    // if (this.Image!.value === '' && this.Image!.touched){
    //   this.mErrImage = "La imagen es requerida (. )( .)";
    //   return true;
    // }
    if (this.imgBandera) {
      return true;
    }
    if (this.Image!.touched) {
      if (this.Image!.hasError('required')) {
        this.mErrImage = 'La imagen es requerida';
        return true;
      }
    }

    return false;
  }

  onFileSelected(event: any) {
    const imgFile: File = event.target.files[0];
    // console.log(imgFile);
    this.imgBandera = false;
    // console.log(imgFile.type);
    if (imgFile) {
      if (imgFile.type !== 'image/jpeg' && imgFile.type !== 'image/png') {
        this.mErrImage = 'El formato de imagen no es correcto';
        this.imgBandera = true;
        this.Image!.setErrors({ status: 'INVALID' });
      }
      if (imgFile.size >= 5000000) {
        // casi 5mb, esta expresado en bytes
        this.mErrImage = 'El tamaño de la imagen es muy grande';
        this.imgBandera = true;
        this.Image!.setErrors({ status: 'INVALID' });
      }
      //si es el formato que espero recibir guardo el archivo en la variable
      if (imgFile.type === 'image/jpeg' || imgFile.type === 'image/png') {
        this.filecito = event.target.files[0];
        this.imgBandera = false;
      }
    } else {
      this.imgBandera = false;
    }
    // console.log(this.filecito);
  }

  onSubmit(event: Event) {
    event.preventDefault();
    if (this.formBiographyImg.valid) {
      this.spinner = true;
      //agregar el switch
      if (this.whatEdit === 'biography') {
        const formData: FormData = new FormData();
        formData.append('img', this.filecito);
        // console.log(this.filecito);
        this.serviceBio.setBioImage(this.id, formData).subscribe({
          next: (result: any) => {
            // console.log("response: ");
            // console.log(result);
            this.mErrMessage = '';
            this.toastr.success('Foto modificada correctamente.', 'Bien!', {
              timeOut: 3000,
              positionClass: 'toast-bottom-right'
            });
          },
          error: (e: any) => {
            // console.log("errorcito");
            // console.log(e);
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
            this.toastr.error(
              'Error al intentar actualizar la foto.',
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
      if (this.whatEdit === 'experience') {
        const formData: FormData = new FormData();
        formData.append('img', this.filecito);
        // console.log("id (expe): " + this.id);
        // console.log(this.filecito);
        this.serviceExpe.setExpeImage(this.id, formData).subscribe({
          next: (result: any) => {
            // console.log("response: ");
            // console.log(result);
            this.mErrMessage = '';
            this.toastr.success('Logotipo modificado correctamente.', 'Bien!', {
              timeOut: 3000,
              positionClass: 'toast-bottom-right'
            });
          },
          error: (e: any) => {
            // console.log("errorcito");
            // console.log(e);
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
            this.toastr.error(
              'Error al intentar actualizar el logotipo.',
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
      if (this.whatEdit === 'academic') {
        const formData: FormData = new FormData();
        formData.append('img', this.filecito);
        // console.log("id (expe): " + this.id);
        // console.log(this.filecito);
        this.serviceAca.setAcademImage(this.id, formData).subscribe({
          next: (result: any) => {
            // console.log("response: ");
            // console.log(result);
            this.mErrMessage = '';
            this.toastr.success('Logo modificado correctamente.', 'Bien!', {
              timeOut: 3000,
              positionClass: 'toast-bottom-right'
            });
          },
          error: (e: any) => {
            // console.log("errorcito");
            // console.log(e);
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
            this.toastr.error(
              'Error al intentar actualizar su Formación Academica.',
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
      if (this.whatEdit === 'skill') {
        // console.log("no hay imagen");
      }
      if (this.whatEdit === 'project') {
        const formData: FormData = new FormData();
        formData.append('img', this.filecito);
        // console.log("id (expe): " + this.id);
        // console.log(this.filecito);
        this.servicePro.setProjectImage(this.id, formData).subscribe({
          next: (result: any) => {
            // console.log("response: ");
            // console.log(result);
            this.mErrMessage = '';
            this.toastr.success('Logo modificado correctamente.', 'Bien!', {
              timeOut: 3000,
              positionClass: 'toast-bottom-right'
            });
          },
          error: (e: any) => {
            // console.log("errorcito");
            // console.log(e);
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
            this.toastr.error(
              'Error al intentar actualizar el logotipo.',
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
      // console.log("el fomrulario es valido");
    } else {
      this.formBiographyImg.markAllAsTouched();
      // console.log(this.formBiographyImg.value);
      // console.log("el formulario es invalido");
      this.toastr.error('Revise los campos.', 'Error!', {
        timeOut: 3000,
        positionClass: 'toast-bottom-right'
      });
      this.spinner = false;
    }
  }

  closeModal() {
    this.modalActive.close('Modal Closed');
  }

  dismissModal() {
    this.modalActive.dismiss('Cross click');
  }
}
