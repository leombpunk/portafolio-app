import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { faCircleQuestion } from '@fortawesome/free-solid-svg-icons';
import { AcademicService } from '../../services/academic.service';
import { Academics } from 'src/app/models/academic';
import { WalkietalkieService } from 'src/app/services/walkietalkie.service';
import { ToastrService } from 'ngx-toastr';
import { customRegExp } from '../../utils/customRegExp';

@Component({
  selector: 'app-modal-add-edit-academic',
  templateUrl: './modal-add-edit-academic.component.html',
  styleUrls: ['./modal-add-edit-academic.component.css']
})
export class ModalAddEditAcademicComponent implements OnInit {
  faCircleQuestion = faCircleQuestion;

  mErrTitulo: string = '';
  mErrInstituto: String = '';
  mErrLocacion: string = '';
  mErrHabilidades: string = '';
  mErrDesde: string = '';
  mErrHasta: string = '';

  mErrMessage: string = '';
  mErrStatus: string = '';
  mErrStatusText: string = '';

  formAcademic: FormGroup;

  @Input() titleModal: string = '';
  @Input() usuario_id: number = 0;
  @Input() academ: Academics = new Academics();

  spinner: boolean = false;

  constructor(
    private modalActive: NgbActiveModal,
    private formBuilder: FormBuilder,
    private service: AcademicService,
    private comunicationService: WalkietalkieService,
    private toastr: ToastrService
  ) {
    this.formAcademic = this.formBuilder.group({
      id: [
        0,
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(10),
          Validators.pattern(customRegExp.integerPattern)
        ]
      ],
      titulo: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
          Validators.pattern(customRegExp.stringPattern)
        ]
      ],
      institucion: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
          Validators.pattern(customRegExp.stringIntegerPattern)
        ]
      ],
      locacion: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
          Validators.pattern(customRegExp.locationPattern)
        ]
      ],
      habilidades: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(500),
          Validators.pattern(customRegExp.stringIntegerPhrasePattern)
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
  public get Titulo() {
    return this.formAcademic.get('titulo');
  }
  public get Institucion() {
    return this.formAcademic.get('institucion');
  }
  public get Locacion() {
    return this.formAcademic.get('locacion');
  }
  public get Habilidades() {
    return this.formAcademic.get('habilidades');
  }
  public get Desde() {
    return this.formAcademic.get('desde');
  }
  public get Hasta() {
    return this.formAcademic.get('hasta');
  }

  //metodos
  public get TituloValid() {
    return this.Titulo!.touched && !this.Titulo!.valid;
  }
  public get TituloError() {
    if (this.Titulo!.errors && this.Titulo!.touched) {
      if (this.Titulo!.hasError('required')) {
        this.mErrTitulo = 'El titulo es requerido';
        return true;
      }
      if (
        this.Titulo!.errors!['minlength'] ||
        this.Titulo!.errors!['maxlength']
      ) {
        this.mErrTitulo = 'El titulo debe contener de 3 a 50 carateres';
        return true;
      }
      if (this.Titulo!.errors!['pattern']) {
        this.mErrTitulo = 'El titulo contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get InstitucionValid() {
    return this.Institucion!.touched && !this.Institucion!.valid;
  }
  public get InstitucionError() {
    if (this.Institucion!.errors && this.Institucion!.touched) {
      if (this.Institucion!.hasError('required')) {
        this.mErrInstituto = 'El campo Institucion es requerido';
        return true;
      }
      if (
        this.Institucion!.errors!['minlength'] ||
        this.Institucion!.errors!['maxlength']
      ) {
        this.mErrInstituto = 'La Institucion debe contener de 3 a 50 carateres';
        return true;
      }
      if (this.Institucion!.errors!['pattern']) {
        this.mErrInstituto =
          'El nombre de la Institución contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get LocacionValid() {
    return this.Locacion!.touched && !this.Locacion!.valid;
  }
  public get LocacionError() {
    if (this.Locacion!.errors && this.Locacion!.touched) {
      if (this.Locacion!.hasError('required')) {
        this.mErrLocacion = 'El campo Lugar es requerido';
        return true;
      }
      if (
        this.Locacion!.errors!['minlength'] ||
        this.Locacion!.errors!['maxlength']
      ) {
        this.mErrLocacion = 'El campo Lugar debe contener de 3 a 50 carateres';
        return true;
      }
      if (this.Locacion!.errors!['pattern']) {
        this.mErrLocacion = 'El campo Lugar contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get HabilidadesValid() {
    return this.Habilidades!.touched && !this.Habilidades!.valid;
  }
  public get HabilidadesError() {
    if (this.Habilidades!.errors && this.Habilidades!.touched) {
      if (this.Habilidades!.hasError('required')) {
        this.mErrHabilidades = 'El campo Habilidades es requerido';
        return true;
      }
      if (
        this.Habilidades!.errors!['minlength'] ||
        this.Habilidades!.errors!['maxlength']
      ) {
        this.mErrHabilidades =
          'El campo Habilidades debe contener de 5 a 500 carateres';
        return true;
      }
      if (this.Habilidades!.errors!['pattern']) {
        this.mErrHabilidades =
          'El campo Habilidades contiene carecteres no soportados';
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
        this.mErrDesde = 'El campo Desde es requerido';
        return true;
      }
      if (
        this.Desde!.errors!['minlength'] ||
        this.Desde!.errors!['maxlength']
      ) {
        this.mErrDesde = 'El campo Desde debe contener 10 carateres';
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

  onSubmit(event: Event) {
    event.preventDefault();
    // console.log(this.Habilidades);
    if (this.formAcademic.valid) {
      // console.log("form: ");
      console.log(this.formAcademic.value);
      // console.log("el fomrulario es valido");
      this.spinner = true;
      if (this.academ.titulo !== '') {
        //entra cuando edito el registro
        let id: any = this.formAcademic.get('id');
        this.service.putAcademics(id.value, this.formAcademic.value).subscribe({
          next: (result: any) => {
            // console.log("result: ");
            // console.log(result);
            this.toastr.success(
              'Formación Academica actualizada correctamente.',
              'Bien!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
          },
          error: (e: any) => {
            // console.log("error: ");
            console.log(e);
            this.toastr.error(
              'Error al intentar actualizar su Formación Academica.',
              'Error!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
            this.spinner = false;
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
          },
          complete: () => {
            this.comunicationService.actualizarAca(true);
            this.spinner = false;
            this.closeModal();
          }
        });
      } else {
        this.formAcademic.patchValue({
          usuarios_id: this.usuario_id
        });
        this.service.postAcademics(this.formAcademic.value).subscribe({
          next: (result: any) => {
            // console.log("result: ");
            // console.log(result);
            this.toastr.success(
              'Formación Academica agregada correctamente.',
              'Bien!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
            this.mErrMessage = '';
          },
          error: (e: any) => {
            // console.log("error: ");
            // console.log(e);
            this.toastr.error(
              'Error al intentar agregar su Formación Academica.',
              'Error!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
            this.spinner = false;
          },
          complete: () => {
            this.comunicationService.actualizarAca(true);
            this.spinner = false;
            this.closeModal();
          }
        });
      }
    } else {
      this.formAcademic.markAllAsTouched();
      // console.log(this.formAcademic.value);
      // console.log("el formulario es invalido");
      // console.log(this.formAcademic.errors);
      this.toastr.warning('Revise los campos.', 'Atención!', {
        timeOut: 3000,
        positionClass: 'toast-bottom-right'
      });
      this.mErrMessage = 'Revise los campos.';
      this.spinner = false;
    }
  }

  //modals
  closeModal() {
    this.modalActive.close('Modal Closed');
  }

  dismissModal() {
    this.modalActive.dismiss('Cross click');
  }
}
