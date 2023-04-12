import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';
import { Login } from 'src/app/models/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup;
  mErrUser: string = '';
  mErrPass: string = '';
  mErrTokenService: string = '';

  mErrStatus: string = '';
  mErrStatusText: string = '';

  isLogged: boolean = false;
  isLogginFail: boolean = false;
  login: Login = new Login('', '');

  roles: string[] = [];

  spinner: boolean = false;

  constructor(
    private form: FormBuilder,
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.formLogin = this.form.group({
      usuario: ['',[Validators.required, Validators.minLength(4), Validators.maxLength(16)]],
      contrasena: ['',[Validators.required, Validators.minLength(6), Validators.maxLength(16)]]
    });
  }

  ngOnInit(): void {
    //comprobar si se esta loggeado
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
      this.isLogginFail = false;
      this.roles = this.tokenService.getAuthorities();
      this.router.navigate([`/home/${this.tokenService.getUserName()}`]);
    }
  }

  public get User() {
    return this.formLogin.get('usuario');
  }
  public get Pass() {
    return this.formLogin.get('contrasena');
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
    }
    return false;
  }

  //onLogin
  onSubmit(event: Event) {
    event.preventDefault();
    if (this.formLogin.valid) {
      // console.log("el formulario es valido");
      // console.log(this.formLogin.value);
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
      this.login = new Login(this.User!.value, this.Pass!.value);
      // console.log(this.login);
      //enviamos al authService
      this.spinner = true;
      this.authService.login(this.login).subscribe({
        next: (result: any) => {
          //manejar desde aqui el spinner?
          
          // console.log("result: ");
          // console.log(result);
          this.isLogged = true;
          this.isLogginFail = false;

          this.tokenService.setToken(result.token);
          this.tokenService.setUserName(result.usuario);
          this.tokenService.setAuthorities(result.authorities);
          this.roles = result.authorities;
          // console.log(this.tokenService.getUserName());
          this.toastr.success(
            'Exito al iniciar sesión!',
            'Bien!',
            {
              timeOut: 3000,
              positionClass: 'toast-bottom-right'
            }
          );
          //redireccionar a la home, en un futuro me gustaria redireccionar al perfil del usuario logeado
          this.router.navigate([`/home/${this.tokenService.getUserName()}`]);
        },
        error: (e: any) => {
          // console.log("error: ");
          console.log(e);
          this.spinner = false;
          this.isLogged = false;
          this.isLogginFail = true;

          //asumo que esto no funcionara porque no tengo la clase mensaje en el backend
          this.mErrTokenService = e.error.mensaje || e.message;
          this.mErrStatus = e.status;
          this.mErrStatusText = e.statusText;
          // console.log(this.mErrTokenService);
          this.toastr.error(
            'Error al iniciar sesión. ' /*+ this.mErrTokenService*/,
            'Error!',
            {
              timeOut: 3000,
              positionClass: 'toast-bottom-right'
            }
          );
        },
        complete: () => {
          // console.log('complete');
          this.spinner = false;
        }
      });
    } else {
      this.formLogin.markAllAsTouched();
      // console.log("el formulario es invalido");
      // console.log(this.formLogin.value);
      // console.log(this.formLogin.errors);
      this.spinner = false;
      this.toastr.warning(
        'Revise los campos.',
        'Atención!',
        {
          timeOut: 3000,
          positionClass: 'toast-bottom-right'
        }
      );
    }
  }
}
