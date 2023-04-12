import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Skill } from '../models/skills';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.prod';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  private apiUrl = environment.apiURL + "/habilidad";

  constructor(private http: HttpClient) { }

  public getSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.apiUrl + "/traer"); 
  }

  //get
  //get que trae los datos de perfil segun el nombre de usuario
  public getSkillsByUsuario(usuario: any): Observable<Skill[]> {
    return this.http.get<Skill[]>(
      this.apiUrl + `/buscarByUsuario/${usuario.toString()}`
    );
  }

  public postSkill(data: any): Observable<Skill> {
    return this.http.post<Skill>(this.apiUrl + "/crear", data);
  }

  // public putSkill(id: any, data: any): Observable<Skill> {
  //   return this.http.put<Skill>(this.apiUrl + `/editar/${id}`, {},{
  //     params: {
  //       descripcion: data.descripcion,
  //       nivel: data.nivel,
  //       tipo_habilidad_id: data.tipo_habilidad_id,
  //       usuarios_id: data.usuarios_id
  //     }
  //   });
  // }

  public putSkill(id: any, data: any): Observable<Skill> {
    return this.http.put<Skill>(this.apiUrl + `/editar/${id}`, data);
  }

  public deleteSkill(id: any): Observable<Skill> {
    return this.http.delete<Skill>(
      this.apiUrl + `/borrar/${id}`,
      httpOptions
    );
  }
}
