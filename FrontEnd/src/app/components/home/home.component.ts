import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/services/token.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isLogged: boolean = false;
  rutaUsuario: string = '';
  // foundUsuario: boolean = true;

  constructor(
    private tokenService: TokenService,
    private routeActive: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.toastr.info(
      'El contenedor del backend tarda unos minutos en iniciar. Si la operación falla vuelva a intentar luego de unos segundos y aguarde a que se complete. Esto puede suceder varias veces!',
      'Aguarde por favor!',
      {
        
        timeOut: 0,
        extendedTimeOut: 0,
        positionClass: 'toast-center-center'
      }
    );
    this.routeActive.params.subscribe((params: Params) => {
      // this.authService.buscarUsuario(params['usuario']).subscribe({
      //   next: (result: any) => {
      //     console.log('result: (buscarUsuario)');
      //     console.log(result);
      //     // this.foundUsuario = true;
      //     this.rutaUsuario = params['usuario'];
      //   },
      //   error: (err: any) => {
      //     // this.foundUsuario = false;
      //     console.log('error: ');
      //     console.log(err);
      //     this.toastr.error('Usuario vacio', 'Error', {
      //       timeOut: 3000,
      //       positionClass: 'toast-center-center'
      //     });
      //     this.router.navigate([`/login`]);
      //   },
      //   complete: () => {
      //     // this.rutaUsuario = params['usuario'];
      //     // this.foundUsuario = true;
      //     console.log('complete');
      //   }
      // });

      this.rutaUsuario = params['usuario'];
      // if (this.rutaUsuario === '') {
      //   this.toastr.success('Usuario vacio', 'Error', {
      //     timeOut: 3000, positionClass: 'toast-bottom-right'
      //   });
      //   this.router.navigate([`/login`]);
      // }
      // console.log(this.rutaUsuario);
    });

    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  logOut() {
    // console.log('recibiendo el eventemiter');
    this.tokenService.logout();
    this.isLogged = false;
  }
}
