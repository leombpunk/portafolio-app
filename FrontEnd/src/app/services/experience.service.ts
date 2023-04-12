import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Experience } from '../models/experience';
import { environment } from '../../environments/environment.prod';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {
  private apiUrl: string = environment.apiURL + '/experiencia';

  constructor(private http: HttpClient) {}

  //get-all
  public getExperiences(): Observable<Experience[]> {
    return this.http.get<Experience[]>(this.apiUrl + '/traer');
  }

  //get
  //get que trae los datos de perfil segun el nombre de usuario
  public getExperiencesByUsuario(usuario: any): Observable<Experience[]> {
    return this.http.get<Experience[]>(
      this.apiUrl + `/buscarByUsuario/${usuario.toString()}`
    );
  }
  //post
  public postExperience(datos: any): Observable<Experience> {
    return this.http.post<Experience>(this.apiUrl + '/crear', datos);
  }

  // //put
  // public putExperience(id: any, datos: any): Observable<Experience> {
  //   return this.http.put<Experience>(
  //     this.apiUrl + `/editar/${id}`,
  //     {},
  //     {
  //       params: {
  //         cargo: datos.cargo,
  //         empresa: datos.empresa,
  //         desde: datos.desde,
  //         hasta: datos.hasta,
  //         usuarios_id: datos.usuarios_id,
  //         tarea: datos.tarea,
  //         reftelef: datos.reftelef,
  //         refnombre: datos.refnombre
  //       }
  //     }
  //   );
  // }

  //put
  public putExperience(id: any, datos: any): Observable<Experience> {
    return this.http.put<Experience>(
      this.apiUrl + `/editar/${id}`, datos);
  }

  //delete
  public deleteExperience(id: any): Observable<Experience> {
    return this.http.delete<Experience>(
      this.apiUrl + `/borrar/${id}`,
      httpOptions
    );
  }

  //edit image
  public setExpeImage(id: any, data: FormData): Observable<any> {
    return this.http.put(this.apiUrl + `/agregarImg/${id}`, data);
  }
  //delete image
  public deleteExpeImage(id: any) {
    return this.http.delete(this.apiUrl + `/borrarImg/${id}`);
  }
}
