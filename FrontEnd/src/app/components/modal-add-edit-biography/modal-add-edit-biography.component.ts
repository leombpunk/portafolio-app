import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Biography } from '../../models/biography';
import { BiographyService } from '../../services/biography.service';
import { WalkietalkieService } from '../../services/walkietalkie.service';
import { ToastrService } from 'ngx-toastr';
import { customRegExp } from '../../utils/customRegExp';
import { faCircleQuestion } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-modal-add-edit-biography',
  templateUrl: './modal-add-edit-biography.component.html',
  styleUrls: ['./modal-add-edit-biography.component.css']
})
export class ModalAddEditBiographyComponent implements OnInit {
  faCircleQuestion = faCircleQuestion;

  @Input() titleModal: string = '';
  @Input() formBiography: FormGroup;
  @Input() bio: Biography = new Biography();

  mErrTitulo: string = '';
  mErrNombre: string = '';
  mErrApellido: string = '';
  mErrAcercade: string = '';
  mErrCorreo: string = '';
  mErrGithub: string = '';
  mErrLinkedin: string = '';

  mErrMessage: string = '';
  mErrStatus: string = '';
  mErrStatusText: string = '';
  spinner: boolean = false;

  constructor(
    private modalActive: NgbActiveModal,
    private formBuilder: FormBuilder,
    private service: BiographyService,
    private comunicationService: WalkietalkieService,
    private toastr: ToastrService
  ) {
    //podria mandar la foto con el dato de foto
    this.formBiography = this.formBuilder.group({
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
      nombre: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(100),
          Validators.pattern(customRegExp.stringPattern)
        ]
      ],
      apellido: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(100),
          Validators.pattern(customRegExp.stringPattern)
        ]
      ],
      acercade: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(500),
          Validators.pattern(customRegExp.stringIntegerPhrasePattern)
        ]
      ],
      correo: [
        '',
        [Validators.required, Validators.maxLength(100), Validators.email]
      ],
      linkedin: [
        '',
        [
          Validators.minLength(3),
          Validators.maxLength(100),
          Validators.pattern(customRegExp.urlPattern)
        ]
      ],
      github: [
        '',
        [
          Validators.minLength(3),
          Validators.maxLength(100),
          Validators.pattern(customRegExp.urlPattern)
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
  public get Titulo() {
    return this.formBiography.get('titulo');
  }
  public get Nombre() {
    return this.formBiography.get('nombre');
  }
  public get Apellido() {
    return this.formBiography.get('apellido');
  }
  public get Acercade() {
    return this.formBiography.get('acercade');
  }
  public get Correo() {
    return this.formBiography.get('correo');
  }
  public get Linkedin() {
    return this.formBiography.get('linkedin');
  }
  public get Github() {
    return this.formBiography.get('github');
  }

  //properties
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

  public get NombreValid() {
    return this.Nombre!.touched && !this.Titulo!.valid;
  }
  public get NombreError() {
    if (this.Nombre!.errors && this.Nombre!.touched) {
      if (this.Nombre!.hasError('required')) {
        this.mErrNombre = 'El nombre es requerido';
        return true;
      }
      if (
        this.Nombre!.errors!['minlength'] ||
        this.Nombre!.errors!['maxlength']
      ) {
        this.mErrNombre = 'El nombre debe contener de 3 a 100 carateres';
        return true;
      }
      if (this.Nombre!.errors!['pattern']) {
        this.mErrNombre = 'El nombre contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get ApellidoValid() {
    return this.Apellido!.touched && !this.Apellido!.valid;
  }
  public get ApellidoError() {
    if (this.Apellido!.errors && this.Apellido!.touched) {
      if (this.Apellido!.hasError('required')) {
        this.mErrApellido = 'El apellido es requerido';
        return true;
      }
      if (
        this.Apellido!.errors!['minlength'] ||
        this.Apellido!.errors!['maxlength']
      ) {
        this.mErrApellido = 'El apellido debe contener de 3 a 100 carateres';
        return true;
      }
      if (this.Apellido!.errors!['pattern']) {
        this.mErrApellido = 'El apellido contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get AcercadeValid() {
    return this.Acercade!.touched && !this.Acercade!.valid;
  }
  public get AcercadeError() {
    if (this.Acercade!.errors && this.Acercade!.touched) {
      if (this.Acercade!.hasError('required')) {
        this.mErrAcercade = 'La seccion acerca de tí es requerida';
        return true;
      }
      if (
        this.Acercade!.errors!['minlength'] ||
        this.Acercade!.errors!['maxlength']
      ) {
        this.mErrAcercade =
          'La seccion acerca de tí debe contener de 3 a 500 carateres';
        return true;
      }
      if (this.Acercade!.errors!['pattern']) {
        this.mErrAcercade =
          'La seccion acerca de tí contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get CorreoValid() {
    return this.Correo!.touched && !this.Correo!.valid;
  }
  public get CorreoError() {
    if (this.Correo!.errors && this.Correo!.touched) {
      if (this.Correo!.hasError('required')) {
        this.mErrCorreo = 'El correo es requerido';
        return true;
      }
      if (
        this.Correo!.errors!['minlength'] ||
        this.Correo!.errors!['maxlength']
      ) {
        this.mErrCorreo = 'El correo debe contener de 3 a 100 carateres';
        return true;
      }
      if (this.Correo!.hasError('email')) {
        this.mErrCorreo = 'El correo no tiene el formato correcto.';
        return true;
      }
    }
    return false;
  }

  public get GithubValid() {
    return this.Github!.touched && !this.Github!.valid;
  }
  public get GithubError() {
    if (this.Github!.errors && this.Github!.touched) {
      if (
        this.Github!.errors!['minlength'] ||
        this.Github!.errors!['maxlength']
      ) {
        this.mErrGithub = 'La url de github debe contener de 3 a 100 carateres';
        return true;
      }
      if (this.Github!.errors!['pattern']) {
        this.mErrGithub = 'La url de github contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  public get LinkedinValid() {
    return this.Linkedin!.touched && !this.Linkedin!.valid;
  }
  public get LinkedinError() {
    if (this.Linkedin!.errors && this.Linkedin!.touched) {
      if (
        this.Linkedin!.errors!['minlength'] ||
        this.Github!.errors!['maxlength']
      ) {
        this.mErrLinkedin =
          'La url de Linkedin debe contener de 3 a 100 carateres';
        return true;
      }
      if (this.Linkedin!.errors!['pattern']) {
        this.mErrLinkedin =
          'La url de Linkedin contiene carecteres no soportados';
        return true;
      }
    }
    return false;
  }

  onSubmit(event: Event) {
    event.preventDefault();
    // console.log("var bio.id: "+this.bio.id);
    // console.log("id submit: "+this.formBiography.get('id'));
    let id: any = this.formBiography.get('id');
    // let response: any;
    if (this.formBiography.valid) {
      this.spinner = true;
      this.service.putBiography(id.value, this.formBiography.value).subscribe({
        next: (result: any) => {
          // response = result;
          // console.log("response: ");
          // console.log(response);
          this.mErrMessage = '';
          this.toastr.success(
            'Información personal actualizada correctamente.',
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
            'Error al intentar actualizar su perfil.',
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
          this.spinner = false;
          this.closeModal();
        }
      });
    } else {
      this.formBiography.markAllAsTouched();
      // console.log(this.formBiography.value);
      // console.log("el formulario es invalido");
      this.mErrMessage = 'Revise los campos.';
      this.toastr.error('Por favor revise los campos.', 'Error!', {
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
