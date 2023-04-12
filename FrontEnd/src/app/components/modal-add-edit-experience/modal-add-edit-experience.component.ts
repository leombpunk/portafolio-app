import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ExperienceService } from '../../services/experience.service';
import { WalkietalkieService } from '../../services/walkietalkie.service';
import { Experience } from '../../models/experience';
import { ToastrService } from 'ngx-toastr';
import { customRegExp } from 'src/app/utils/customRegExp';
import { faCircleQuestion } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-modal-add-edit-experience',
  templateUrl: './modal-add-edit-experience.component.html',
  styleUrls: ['./modal-add-edit-experience.component.css']
})
export class ModalAddEditExperienceComponent implements OnInit {
  faCircleQuestion = faCircleQuestion;

  @Input() titleModal: string = '';
  @Input() formExperience: FormGroup;
  @Input() expe: Experience = new Experience();
  @Input() usuario_id: number = 0;

  mErrCargo: string = '';
  mErrEmpresa: string = '';
  mErrDesde: string = '';
  mErrHasta: string = '';
  mErrReftelef: string = '';
  mErrRefnombre: string = '';
  mErrTarea: string = '';

  mErrMessage: string = '';
  mErrStatus: string = '';
  mErrStatusText: string = '';
  spinner: boolean = false;

  constructor(
    private modalActive: NgbActiveModal,
    private form: FormBuilder,
    private service: ExperienceService,
    private comunicationService: WalkietalkieService,
    private toastr: ToastrService
  ) {
    this.formExperience = this.form.group({
      id: [
        0,
        [
          Validators.minLength(1),
          Validators.maxLength(10),
          Validators.pattern(customRegExp.integerPattern)
        ]
      ],
      cargo: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
          Validators.pattern(customRegExp.stringPattern)
        ]
      ],
      empresa: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
          Validators.pattern(customRegExp.stringIntegerPattern)
        ]
      ],
      desde: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10),
          Validators.pattern(customRegExp.datePattern)
        ]
      ],
      hasta: ['', []],
      reftelef: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50),
          Validators.pattern(customRegExp.phonePattern)
        ]
      ],
      refnombre: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50),
          Validators.pattern(customRegExp.stringPattern)
        ]
      ],
      tarea: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(500),
          Validators.pattern(customRegExp.stringIntegerPhrasePattern)
        ]
      ],
      usuarios_id: [
        0,
        [
          Validators.minLength(1),
          Validators.maxLength(10),
          Validators.pattern(customRegExp.integerPattern)
        ]
      ]
    });
  }

  ngOnInit(): void {}

  //getters
  public get Id() {
    return this.formExperience.get('id');
  }
  public get Cargo() {
    return this.formExperience.get('cargo');
  }
  public get Empresa() {
    return this.formExperience.get('empresa');
  }
  public get Desde() {
    return this.formExperience.get('desde');
  }
  public get Hasta() {
    return this.formExperience.get('hasta');
  }
  public get Reftelef() {
    return this.formExperience.get('reftelef');
  }
  public get Refnombre() {
    return this.formExperience.get('refnombre');
  }
  public get Tarea() {
    return this.formExperience.get('tarea');
  }
  //propiedades
  public get CargoValid() {
    return this.Cargo!.touched && !this.Cargo!.valid;
  }
  public get CargoError() {
    if (this.Cargo!.errors && this.Cargo!.touched) {
      if (this.Cargo!.hasError('required')) {
        this.mErrCargo = 'El campo cargo es requerido.';
        return true;
      }
      if (
        this.Cargo!.errors!['minlength'] ||
        this.Cargo!.errors!['maxlength']
      ) {
        this.mErrCargo =
          'El Cargo debe contener de 3 a 50 carateres de longitud.';
        return true;
      }
      if (this.Cargo!.errors!['pattern']) {
        this.mErrCargo = 'El Cargo contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get EmpresaValid() {
    return this.Empresa!.touched && !this.Empresa!.valid;
  }
  public get EmpresaError() {
    if (this.Empresa!.errors && this.Empresa!.touched) {
      if (this.Empresa!.hasError('required')) {
        this.mErrEmpresa = 'El campo Empresa es requerido.';
        return true;
      }
      if (
        this.Empresa!.errors!['minlength'] ||
        this.Empresa!.errors!['maxlength']
      ) {
        this.mErrEmpresa =
          'El campo Empresa debe contener de 3 a 50 carateres de longitud.';
        return true;
      }
      if (this.Empresa!.errors!['pattern']) {
        this.mErrEmpresa = 'El campo Empresa contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get DesdeValid() {
    return this.Desde!.touched && !this.Desde!.valid;
  }
  public get DesdeError() {
    if (this.Desde!.errors && this.Desde!.touched) {
      if (this.Desde!.hasError('required')) {
        this.mErrDesde = 'El campo Desde es requerido.';
        return true;
      }
      if (
        this.Desde!.errors!['minlength'] ||
        this.Desde!.errors!['maxlength']
      ) {
        this.mErrDesde =
          'El campo Desde debe contener 8 carateres de longitud.';
        return true;
      }
      if (this.Desde!.errors!['pattern']) {
        this.mErrDesde = 'El campo Desde contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get HastaValid() {
    return this.Hasta!.touched && !this.Hasta!.valid;
  }
  public get HastaError() {
    //console.log(this.Hasta!.value);//muestra el dato cuando esta completa la entrada dia mes año
    // if (this.Hasta!.errors && this.Hasta!.touched){
    //   if (this.Hasta!.errors!['minlength'] || this.Hasta!.errors!['maxlength']){
    //     this.mErrHasta = "El campo Hasta debe contener 8 carateres de longitud.";
    //     return true;
    //   }
    // }
    // return false;
    if (this.Hasta!.touched) {
      this.Hasta!.setErrors(null);
      // console.log(this.Hasta!.errors);
      if (this.Hasta!.value !== '') {
        // si es distinto que vacio procedo a validar
        const value: string = this.Hasta!.value;
        //comprobar longitud = 10
        if (value.length < 10 || value.length > 10) {
          this.Hasta!.setErrors({ minlength: true });
          this.mErrHasta = 'El campo Hasta debe contener 10 carateres';
          return true;
        }
        const desde: string = this.Desde!.value;
        const hasta: string = this.Hasta!.value;
        const fechaDesde = new Date(desde);
        const fechahasta = new Date(hasta);
        const fechaHoy = new Date();
        //comprobar que sea mayor que el campo Desde
        if (fechaDesde.getTime() > fechahasta.getTime()) {
          this.Hasta!.setErrors({ minor: true });
          this.mErrHasta =
            'El campo Hasta no puede contener una fecha anterior al campo desde';
          return true;
        }
        // console.log("yo introduje: 2022-12-10 (año/mes/dia)"); //rompe los huevos con el utc o gtm
        // console.log("new date: "+fechaDesde);
        // console.log("getDate: "+fechaDesde.getDate());
        // console.log("getUTCDate: "+fechaDesde.getUTCDate());
        // console.log("getMonth: "+fechaDesde.getMonth());

        //comprobar que no se ponga una fecha mayor a la del dia
        if (fechahasta.getTime() > fechaHoy.getTime()) {
          this.Hasta!.setErrors({ manor: true });
          this.mErrHasta =
            'El campo Hasta no puede contener una fecha mayor a la actual';
          return true;
        }
        //comprobar con el pattern datePatter
        if (!value.match(customRegExp.datePattern)) {
          this.Hasta!.setErrors({ pattern: true });
          this.mErrHasta = 'El campo Desde contiene carecteres no soportados';
          return true;
        }
      }
    }
    return false;
  }

  public get ReftelefValid() {
    return this.Reftelef!.touched && !this.Reftelef!.valid;
  }
  public get ReftelefError() {
    if (this.Reftelef!.errors && this.Reftelef!.touched) {
      if (this.Reftelef!.hasError('required')) {
        this.mErrReftelef = 'El campo Contacto de Referencia es requerido.';
        return true;
      }
      if (
        this.Reftelef!.errors!['minlength'] ||
        this.Reftelef!.errors!['maxlength']
      ) {
        this.mErrReftelef =
          'El campo Contacto de Referencia debe contener entre 5 a 50 carateres.';
        return true;
      }
      if (this.Reftelef!.errors!['pattern']) {
        this.mErrReftelef =
          'El campo Contacto contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get RefnombreValid() {
    return this.Refnombre!.touched && !this.Refnombre!.valid;
  }
  public get RefnombreError() {
    if (this.Refnombre!.errors && this.Refnombre!.touched) {
      if (this.Refnombre!.hasError('required')) {
        this.mErrRefnombre =
          'El campo Nombre de Contacto de Referencia es requerido.';
        return true;
      }
      if (
        this.Refnombre!.errors!['minlength'] ||
        this.Refnombre!.errors!['maxlength']
      ) {
        this.mErrRefnombre =
          'El campo Nombre Contacto de Referencia debe contener entre 5 a 50 carateres.';
        return true;
      }
      if (this.Refnombre!.errors!['pattern']) {
        this.mErrRefnombre =
          'El campo Nombre Contacto de Referencia contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get TareaValid() {
    return this.Tarea!.touched && !this.Tarea!.valid;
  }
  public get TareaError() {
    if (this.Tarea!.errors && this.Tarea!.touched) {
      if (this.Tarea!.hasError('required')) {
        this.mErrTarea = 'El campo tarea es requerido.';
        return true;
      }
      if (
        this.Tarea!.errors!['minlength'] ||
        this.Tarea!.errors!['maxlength']
      ) {
        this.mErrTarea =
          'El campo tarea debe contener entre 5 a 500 carateres.';
        return true;
      }
      if (this.Tarea!.errors!['pattern']) {
        this.mErrTarea = 'El campo Tarea contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  onSubmit(event: Event) {
    event.preventDefault();
    // console.log("form: ");
    if (this.formExperience.valid) {
      this.spinner = true;
      // pregunto si la varialbe 'expe' tiene datos
      //si los tiene, hice la llamada por el boton de editar
      if (this.expe.cargo !== '') {
        let id: any = this.formExperience.get('id');
        this.service
          .putExperience(id.value, this.formExperience.value)
          .subscribe({
            next: (result: any) => {
              // console.log("result: ");
              // console.log(result);
              this.mErrMessage = '';
              this.toastr.success(
                'Experiencia laboral actualizada correctamente.',
                'Bien!',
                {
                  timeOut: 3000,
                  positionClass: 'toast-bottom-right'
                }
              );
            },
            error: (e: any) => {
              // console.log("errorcito");
              // console.log(e);
              // console.log(e.ok);
              this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
              this.toastr.error(
                'Error al intentar actualizar su Experiencia laboral.',
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
      //si en cambio esta vacio, hice la llamada por el boton añadir
      else {
        this.formExperience.patchValue({
          usuarios_id: this.usuario_id
        });
        // console.log(this.formExperience.value);
        // console.log(this.formExperience.getRawValue());
        this.service.postExperience(this.formExperience.value).subscribe({
          next: (result: any) => {
            // console.log("result: ");
            // console.log(result);
            this.mErrMessage = '';
            this.toastr.success(
              'Experiencia labolral agregada correctamente.',
              'Bien!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
          },
          error: (e: any) => {
            // console.log("errorcito");
            // console.log(e);
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
            this.toastr.error(
              'Error al intentar agregar su Experiencia laboral.',
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
    } else {
      this.formExperience.markAllAsTouched();
      // console.log(this.formExperience.value);
      // console.log("el formulario es invalido");
      this.mErrMessage = 'Revise los campos.';
    }
  }

  closeModal() {
    this.modalActive.close('Modal Closed');
  }

  dismissModal() {
    this.modalActive.dismiss('Cross click');
  }
}
