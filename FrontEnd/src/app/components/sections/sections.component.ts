import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Biography } from '../../models/biography';
import { Academics } from '../../models/academic';
import { Experience } from '../../models/experience';
import { Project } from '../../models/projects';
import { Skill } from '../../models/skills';
import { BiographyService } from 'src/app/services/biography.service';
import { ExperienceService } from 'src/app/services/experience.service';
import { AcademicService } from '../../services/academic.service';
import { ProjectService } from '../../services/project.service';
import { SkillService } from '../../services/skill.service';
import { WalkietalkieService } from '../../services/walkietalkie.service';
import { AuthService } from 'src/app/services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sections',
  templateUrl: './sections.component.html',
  styleUrls: ['./sections.component.css']
})
export class SectionsComponent implements OnInit {

  //private urlImageApi: string = 'http://localhost:8080/images/';

  @Input() isLogged: boolean = false;
  @Input() rutaUsuario: string = '';

  bio: Biography = new Biography();
  expe: Experience[] = new Array<Experience>();
  academ: Academics[] = new Array<Academics>();
  projects: Project[] = new Array<Project>();
  skills: Skill[] = new Array<Skill>();

  constructor(
    private serviceBio: BiographyService,
    private serviceExpe: ExperienceService,
    private serviceAcadm: AcademicService,
    private serviceProject: ProjectService,
    private serviceSkill: SkillService,
    private comunicationService: WalkietalkieService,
    private toastr: ToastrService,
    private authService: AuthService,
    private router: Router
  ) {
    this.comunicationService.informarBio$.subscribe((value: boolean) => {
      if (value) {
        this.UpdateEvent();
      }
    });

    this.comunicationService.informarExpe$.subscribe((value: boolean) => {
      if (value) {
        this.UpdateEventExpe();
      }
    });

    this.comunicationService.informarAca$.subscribe((value: boolean) => {
      if (value) {
        this.UpdateEventAcadem();
      }
    });

    this.comunicationService.informarProj$.subscribe((value: boolean) => {
      if (value) {
        this.UpdateEventProject();
      }
    });

    this.comunicationService.informarSkill$.subscribe((value: boolean) => {
      if (value) {
        this.UpdateEventSkill();
      }
    });
  }

  ngOnInit(): void {
    // console.log("ruta: "+this.rutaUsuario);
    this.authService.buscarUsuario(this.rutaUsuario).subscribe({
      next: (result: any) => {
        // console.log('result: (buscarUsuario)');
        // console.log(result);
        this.LoadData();
        this.LoadDataExperience();
        this.LoadDataAcademic();
        this.LoadDataProject();
        this.LoadDataSkill();
      },
      error: (err: any) => {
        // console.log('error: ');
        // console.log(err);
        this.toastr.error('Usuario vacio', 'Error', {
          timeOut: 3000,
          positionClass: 'toast-center-center'
        });
        this.router.navigate([`/login`]);
      },
      complete: () => {
        // console.log('complete');
      }
    });
  }

  LoadData() {
    this.serviceBio
      .getPerfilByUsuario(this.rutaUsuario)
      .subscribe((bio: any) => {
        this.bio = bio;
        this.bio.foto = /*this.urlImageApi +*/ bio.foto;
        this.comunicationService.setUsuarioId(this.bio.usuarios_id);
        // console.log("bio (desde sections LoadData): ");
        // console.log(bio);
        // this.onInit.emit(this.bio.nombre+" "+this.bio.apellido);
        this.comunicationService.nombrePerfil(this.bio.nombre+" "+this.bio.apellido);
      });
  }

  LoadDataExperience() {
    this.serviceExpe
      .getExperiencesByUsuario(this.rutaUsuario)
      .subscribe((expe: any) => {
        expe.forEach((element: any) => {
          let esplitear: string[] = element.tarea.split(',');
          // console.log(esplitear);
          element.tarea = esplitear;
          element.logo = /*this.urlImageApi +*/ element.logo;
        });
        this.expe = expe;
      });
  }

  LoadDataAcademic() {
    this.serviceAcadm
      .getAcademicsByUsuario(this.rutaUsuario)
      .subscribe((academ: any) => {
        academ.forEach((element: any) => {
          let esplitear: string[] = element.habilidades.split(',');
          // console.log(esplitear);
          element.habilidades = esplitear;
          element.logo = /*this.urlImageApi +*/ element.logo;
        });
        this.academ = academ;
      });
  }

  LoadDataSkill() {
    this.serviceSkill
      .getSkillsByUsuario(this.rutaUsuario)
      .subscribe((skill: any) => {
        this.skills = skill;
      });
  }

  LoadDataProject() {
    this.serviceProject
      .getProjectsByUsuario(this.rutaUsuario)
      .subscribe((project: any) => {
        project.forEach((element: any) => {
          element.logo = /*this.urlImageApi +*/ element.logo;
          // console.log("element: ");
          // console.log(element);
        });
        this.projects = project;
      });
  }

  UpdateEvent() {
    // console.log('update event');
    this.LoadData();
    this.comunicationService.actualiceBio(false);
  }

  UpdateEventExpe() {
    this.LoadDataExperience();
    this.comunicationService.actualiceExpe(false);
  }

  UpdateEventAcadem() {
    this.LoadDataAcademic();
    this.comunicationService.actualiceAca(false);
  }

  UpdateEventProject() {
    this.LoadDataProject();
    this.comunicationService.actualiceProj(false);
  }

  UpdateEventSkill() {
    this.LoadDataSkill();
    this.comunicationService.actualiceSkill(false);
  }
}
