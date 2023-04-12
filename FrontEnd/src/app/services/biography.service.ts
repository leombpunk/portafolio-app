import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Biography } from '../models/biography';
import { environment } from '../../environments/environment.prod';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class BiographyService {
  private apiUrl: string = environment.apiURL + '/perfil';

  constructor(private http: HttpClient) {}

  //get
  //este get debe recibir un parametro 'nombre de usuario'
  public getBiography(): Observable<Biography> {
    return this.http.get<Biography>(this.apiUrl + '/buscar/1');
  }
  //get que trae los datos de perfil segun el nombre de usuario
  public getPerfilByUsuario(usuario: any): Observable<Biography> {
    return this.http.get<Biography>(this.apiUrl + `/buscarByUsuario/${usuario.toString()}`);
  }

  //delete
  public deleteBiography(id: any): Observable<Biography> {
    return this.http.delete<Biography>(
      this.apiUrl + `/borrar/${id}`,
      httpOptions
    );
  }

  //put
  /*public putBiography(id: any, data: any): Observable<Biography> {
    // console.log('desde servicio<put>: ' + data.id + ' ' + data.nombre);
    return this.http.put<Biography>(
      this.apiUrl + `/editar/${id}`,
      {},
      {
        params: {
          nombre: data.nombre,
          apellido: data.apellido,
          titulo: data.titulo,
          correo: data.correo,
          acercade: data.acercade,
          github: data.github,
          linkedin: data.linkedin,
        }
      }
    );
  }*/

  //put
  public putBiography(id: any, data: any): Observable<Biography> {
    // console.log('desde servicio<put>: ' + data.id + ' ' + data.nombre);
    return this.http.put<Biography>(
      this.apiUrl + `/editar/${id}`, data);
  }
  
  public setBioImage(id: any, data: FormData): Observable<any>{
    return this.http.put(this.apiUrl + `/agregarImg/${id}`, data);
  }

  public deleteBioImage(id: any){
    return this.http.delete(this.apiUrl + `/borrarImg/${id}`);
  }
}
