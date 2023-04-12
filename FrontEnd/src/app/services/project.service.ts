import { Injectable } from '@angular/core';
import { Project } from '../models/projects';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.prod';

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type":"application/json"
  })
}

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private apiUrl = environment.apiURL + "/proyecto";

  constructor(private http: HttpClient) { }

  public getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.apiUrl + "/traer");
  }

  //get
  //get que trae los datos de perfil segun el nombre de usuario
  public getProjectsByUsuario(usuario: any): Observable<Project[]> {
    return this.http.get<Project[]>(
      this.apiUrl + `/buscarByUsuario/${usuario.toString()}`
    );
  }

  public postProject(datos: any): Observable<Project> {
    return this.http.post<Project>(this.apiUrl + "/crear", datos);
  }

  // public putProject(id: any, datos: any): Observable<Project> {
  //   return this.http.put<Project>(this.apiUrl + `/editar/${id}`,{}, {
  //     params: {
  //       nombre: datos.nombre,
  //       descripcion: datos.descripcion,
  //       sitio: datos.sitio,
  //       enlace: datos.enlace,
  //       desde: datos.desde,
  //       hasta: datos.hasta,
  //       usuarios_id: datos.usuarios_id
  //     }
  //   });
  // }

  public putProject(id: any, datos: any): Observable<Project> {
    return this.http.put<Project>(this.apiUrl + `/editar/${id}`, datos);
  }

  public deleteProject(id: any): Observable<Project> {
    return this.http.delete<Project>(
      this.apiUrl + `/borrar/${id}`,
      httpOptions
    );
  }

  public setProjectImage(id: any, data: FormData): Observable<any>{
    return this.http.put(this.apiUrl + `/agregarImg/${id}`, data);
  }

  public deleteProjectImage(id: any){
    return this.http.delete(this.apiUrl + `/borrarImg/${id}`);
  }
}
