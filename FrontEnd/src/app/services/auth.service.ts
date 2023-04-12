import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UsuarioNuevo } from '../models/usuario-nuevo';
import { Observable } from 'rxjs';
import { Login } from '../models/login';
import { JwtDTO } from '../models/jwt-DTO';
import { environment } from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authURL: string = environment.apiURL + "/auth/";

  constructor(private http: HttpClient) { }

  public login(usuario: Login): Observable<JwtDTO>{
    return this.http.post<JwtDTO>(this.authURL + "login", usuario);
  }

  //no hay componente para esta parte del codigo
  public nuevoUsuario(usuario: UsuarioNuevo): Observable<any>{
    return this.http.post<any>(this.authURL + "nuevoUsuario", usuario);
  }

  public buscarUsuario(usuario: any): Observable<any>{
    return this.http.get(this.authURL + `buscarUsuario/${usuario}`);
  }
}
