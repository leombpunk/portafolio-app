import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { UsuarioNuevo } from 'src/app/models/usuario-nuevo';
import { TokenService } from 'src/app/services/token.service';
import { ToastrService } from 'ngx-toastr';
import { customRegExp } from 'src/app/utils/customRegExp';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
  mErrResgistro: string = '';
  mErrUser: string = '';
  mErrPass: string = '';
  mErrStatus: string = '';
  mErrStatusText: string = '';

  isLogged: boolean = false;
  isRegistred: boolean = false;
  isRegistredFail: boolean = false;
  formRegistro: FormGroup;
  usuarioNuevo: UsuarioNuevo = new UsuarioNuevo('', '');
  spinner: boolean = false;

  constructor(
    private form: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private tokenService: TokenService,
    private toastr: ToastrService
  ) {
    this.formRegistro = this.form.group({
      usuario: [
        '',
        [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(16),
          Validators.pattern(customRegExp.userNamePassword)
        ]
      ],
      contrasena: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(16),
          Validators.pattern(customRegExp.userNamePassword)
        ]
      ]
    });
  }

  ngOnInit(): void {
    // this.toastr.info(
    //   'El web service de Render.com es lento, puede demorar hasta 5 minutos hasta arrancar el contenedor',
    //   'Aguarde por favor!',
    //   {
    //     timeOut: 0,
    //     extendedTimeOut: 0,
    //     positionClass: 'toast-center-center'
    //   }
    // );
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  public get User() {
    return this.formRegistro.get('usuario');
  }
  public get Pass() {
    return this.formRegistro.get('contrasena');
  }

  public get UserValid() {
    return this.User!.touched && !this.User!.valid;
  }
  public get UserError() {
    if (this.User!.touched && this.User!.errors) {
      if (this.User!.hasError('required')) {
        this.mErrUser = 'El usuario es requerido';
        return true;
      }
      if (this.User!.errors!['minlength'] || this.User!.errors!['maxlength']) {
        this.mErrUser = 'El usuario debe contener entre 4 y 16 caracteres';
        return true;
      }
      if (this.User!.errors!['pattern']) {
        this.mErrUser = 'El usuario acpeta numeros 0-9, letras a-z y A-Z';
        return true;
      }
    }
    return false;
  }

  public get PassValid() {
    return this.Pass!.touched && !this.Pass!.valid;
  }
  public get PassError() {
    if (this.Pass!.touched && this.Pass!.errors) {
      if (this.Pass!.hasError('required')) {
        this.mErrPass = 'La contraseña es requerida';
        return true;
      }
      if (this.Pass!.errors!['minlength'] || this.Pass!.errors!['maxlength']) {
        this.mErrPass = 'La contraseña debe contener entre 6 y 16 caracteres';
        return true;
      }
      if (this.Pass!.errors!['pattern']) {
        this.mErrPass = 'La contraseña acpeta numeros 0-9, letras a-z y A-Z';
        return true;
      }
    }
    return false;
  }

  onSubmit(event: Event) {
    event.preventDefault();
    if (this.formRegistro.valid) {
      // console.log("el formulario es valido");
      // console.log(this.formRegistro.value);
      this.toastr.info(
        'El contenedor del backend tarda unos minutos en iniciar. Si la operación falla vuelva a intentar luego de unos segundos y aguarde a que se complete. Esto puede suceder varias veces!',
        'Aguarde por favor!',
        {
          timeOut: 0,
          extendedTimeOut: 0,
          positionClass: 'toast-center-center'
        }
      );
      //instancio el la clase login
      this.usuarioNuevo = new UsuarioNuevo(this.User!.value, this.Pass!.value);
      // console.log(this.usuarioNuevo);
      //enviamos al authService
      this.spinner = true;
      this.authService.nuevoUsuario(this.usuarioNuevo).subscribe({
        next: (result: any) => {
          // console.log("result: ");
          // console.log(result);
          this.isRegistred = true;
          this.isRegistredFail = false;
          this.toastr.success('Cuenta Creada', 'Bien', {
            timeOut: 3000,
            positionClass: 'toast-bottom-right'
          });
          this.router.navigate(['/login']);
        },
        error: (e: any) => {
          // console.log("error: ");
          // console.log(e);
          this.spinner = false;
          this.isRegistred = false;
          this.isRegistredFail = true;
          //asumo que esto no funcionara porque no tengo la clase mensaje en el backend
          this.mErrResgistro = e.error.mensaje || e.message;
          this.mErrStatus = e.status;
          this.mErrStatusText = e.statusText;

          this.toastr.error('No se puede registrar. '+this.mErrResgistro, 'Error', {
            timeOut: 3000,
            positionClass: 'toast-bottom-right'
          });
        },
        complete: () => {
          // console.log("complete");
          this.spinner = false;
        }
      });
    } else {
      this.formRegistro.markAllAsTouched();
      // console.log("el formulario es invalido");
      // console.log(this.formRegistro.value);
      // console.log(this.formRegistro.errors);
      this.toastr.warning('Revisa los campos.', 'Ateención!', {
        timeOut: 3000,
        positionClass: 'toast-bottom-right'
      });
    }
  }
}
