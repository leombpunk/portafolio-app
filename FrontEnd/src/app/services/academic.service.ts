import { Injectable } from '@angular/core';
import { Academics } from '../models/academic';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment.prod';
const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type":"application/json"
  })
}

@Injectable({
  providedIn: 'root'
})
export class AcademicService {

  private apiUrl: string = environment.apiURL + "/educacion";

  constructor(private http: HttpClient) { }

  public getAcademics(): Observable<Academics[]> {
    return this.http.get<Academics[]>(this.apiUrl + "/traer");
  }

  //get
  //get que trae los datos de perfil segun el nombre de usuario
  public getAcademicsByUsuario(usuario: any): Observable<Academics[]> {
    return this.http.get<Academics[]>(
      this.apiUrl + `/buscarByUsuario/${usuario.toString()}`
    );
  }

  public postAcademics(datos: any): Observable<Academics> {
    return this.http.post<Academics>(this.apiUrl + "/crear", datos);
  }

  /*public putAcademics(id: any, datos: any): Observable<Academics> {
    return this.http.put<Academics>(this.apiUrl + `/editar/${id}`,{}, {
      params: {
        titulo: datos.titulo,
        institucion: datos.institucion,
        locacion: datos.locacion,
        habilidades: datos.habilidades,
        desde: datos.desde,
        hasta: datos.hasta,
        usuarios_id: datos.usuarios_id
      }
    });
  }*/

  public putAcademics(id: any, datos: any): Observable<Academics> {
    return this.http.put<Academics>(this.apiUrl + `/editar/${id}`, datos);
  }

  public deleteAcademic(id: any): Observable<Academics> {
    return this.http.delete<Academics>(
      this.apiUrl + `/borrar/${id}`,
      httpOptions
    );
  }

  public setAcademImage(id: any, data: FormData): Observable<any>{
    return this.http.put(this.apiUrl + `/agregarImg/${id}`, data);
  }

  public deleteAcademImage(id: any){
    return this.http.delete(this.apiUrl + `/borrarImg/${id}`);
  }

}
