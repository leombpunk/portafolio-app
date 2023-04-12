import { Component, OnInit, Input } from '@angular/core';
import { faCircleQuestion } from '@fortawesome/free-solid-svg-icons';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Project } from 'src/app/models/projects';
import { ProjectService } from 'src/app/services/project.service';
import { WalkietalkieService } from 'src/app/services/walkietalkie.service';
import { ToastrService } from 'ngx-toastr';
import { customRegExp } from 'src/app/utils/customRegExp';

@Component({
  selector: 'app-modal-add-edit-project',
  templateUrl: './modal-add-edit-project.component.html',
  styleUrls: ['./modal-add-edit-project.component.css']
})
export class ModalAddEditProjectComponent implements OnInit {

  faCircleQuestion = faCircleQuestion;

  @Input() titleModal: string = "";
  @Input() usuario_id: number = 0;

  formProject: FormGroup;

  mErrNombre: string = "";
  mErrDescripcion: string = "";
  mErrDesde: string = "";
  mErrHasta: string = "";
  mErrSitio: string = "";
  mErrEnlace: string = "";

  mErrMessage: string = '';
  mErrStatus: string = '';
  mErrStatusText: string = '';
  spinner: boolean = false;

  @Input() project: Project = new Project();
  
  constructor(
    private modalActive: NgbActiveModal, 
    private form: FormBuilder, 
    private service: ProjectService, 
    private comunicationService: WalkietalkieService,
    private toastr: ToastrService) { 
    this.formProject = this.form.group({
      id: [0,[Validators.required, Validators.minLength(1), Validators.maxLength(10), Validators.pattern(customRegExp.integerPattern)]],
      usuarios_id: [0,[Validators.required, Validators.minLength(1), Validators.maxLength(10), Validators.pattern(customRegExp.integerPattern)]],
      nombre: ['',[Validators.required, Validators.minLength(5), Validators.maxLength(50), Validators.pattern(customRegExp.stringIntegerPattern)]],
      descripcion: ['',[Validators.required, Validators.minLength(5), Validators.maxLength(500), Validators.pattern(customRegExp.stringIntegerPhrasePattern)]],
      desde: ['',[Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern(customRegExp.datePattern)]],
      hasta: ['',[]],
      sitio: ['',[Validators.maxLength(100), Validators.pattern(customRegExp.urlPattern)]],
      enlace: ['',[Validators.maxLength(100), Validators.pattern(customRegExp.urlPattern)]]
    });
  }

  ngOnInit(): void { }

  //getters
  public get Nombre(){
    return this.formProject.get('nombre');
  }
  public get Descripcion(){
    return this.formProject.get('descripcion');
  }
  public get Desde(){
    return this.formProject.get('desde');
  }
  public get Hasta(){
    return this.formProject.get('hasta');
  }
  public get Sitio(){
    return this.formProject.get('sitio');
  }
  public get Enlace(){
    return this.formProject.get('enlace');
  }

  //metodos
  public get NombreValid(){
    return this.Nombre!.touched && !this.Nombre!.valid;
  }
  public get NombreError(){
    if (this.Nombre!.errors && this.Nombre!.touched) {
      // console.log("primer if");
      if(this.Nombre!.hasError('required')){
        // console.log("segundo if");
        this.mErrNombre = "El Nombre es requerido";
        return true;
      }
      if(this.Nombre!.errors!['minlength'] || this.Nombre!.errors!['maxlength']){
        this.mErrNombre = "El Nombre debe contener de 5 a 50 carateres";
        return true;
      }
      if (this.Nombre!.errors!['pattern']){
        this.mErrNombre = "El nombre contiene caracteres no soportados";
        return true;
      }
    }
    return false;
  }

  public get DescripcionValid(){
    return this.Descripcion!.touched && !this.Descripcion!.valid;
  }
  public get DescripcionError(){
    if (this.Descripcion!.errors && this.Descripcion!.touched){
      if (this.Descripcion!.hasError('required')){
        this.mErrDescripcion = "La descripcion es requerida";
        return true;
      }
      if(this.Descripcion!.errors!['minlength'] || this.Descripcion!.errors!['maxlength']){
        this.mErrDescripcion = "La descripcion debe contener de 5 a 500 carateres";
        return true;
      }
      if (this.Descripcion!.errors!['pattern']){
        this.mErrDescripcion = "La Descripcion contiene caracteres no soportados";
        return true;
      }
    }
    return false;
  }

  public get EnlaceValid(){
    return this.Enlace!.touched && !this.Enlace!.valid;
  }
  public get EnlaceError(){
    if (this.Enlace!.errors && this.Enlace!.touched){
      if(this.Enlace!.errors!['maxlength']){
        this.mErrEnlace = "El enlace/repositorio debe contener un maximo de 100 carateres";
        return true;
      }
      if (this.Enlace!.errors!['pattern']){
        this.mErrEnlace = "El campo enlace/repositorio contiene caracteres no soportados";
        return true;
      }
    }
    return false;
  }

  public get SitioValid(){
    return this.Sitio!.touched && !this.Sitio!.valid;
  }
  public get SitioError(){
    if (this.Sitio!.errors && this.Sitio!.touched){
      if(this.Sitio!.errors!['maxlength']){
        this.mErrSitio = "El sitio/demo debe contener un maximo de 100 carateres";
        return true;
      }
      if (this.Sitio!.errors!['pattern']){
        this.mErrSitio = "El campo sitio/demo contiene caracteres no soportados";
        return true;
      }
    }
    return false;
  }

  public get DesdeValid(){
    return this.Desde!.touched && !this.Desde!.valid;
  }
  public get DesdeError(){
    if (this.Desde!.errors && this.Desde!.touched){
      if (this.Desde!.hasError('required')){
        this.mErrDesde = "El campo inicio es requerido";
        return true;
      }
      if(this.Desde!.errors!['minlength'] || this.Desde!.errors!['maxlength']){
        this.mErrDesde = "El campo inicio debe contener un minimo y maximo de 10 carateres";
        return true;
      }
      if (this.Desde!.errors!['pattern']){
        this.mErrDesde = "El campo Desde contiene caracteres no soportados";
        return true;
      }
    }
    return false;
  }

  public get HastaValid(){
    return this.Hasta!.touched && !this.Hasta!.valid;
  }
  public get HastaError(){
    // if (this.Hasta!.errors && this.Hasta!.touched){
    //   if(this.Hasta!.errors!['maxlength']){
    //     this.mErrHasta = "El campo fin debe contener un maximo de 10 carateres";
    //     return true;
    //   }
    // }
    // return false;
    if (this.Hasta!.touched){
      this.Hasta!.setErrors(null);
      // console.log(this.Hasta!.errors);
      if (this.Hasta!.value !== ""){ // si es distinto que vacio procedo a validar
        const value: string = this.Hasta!.value;
        //comprobar longitud = 10
        if (value.length < 10 || value.length > 10){
          this.Hasta!.setErrors({ 'minlength': true });
          this.mErrHasta = "El campo Hasta debe contener 10 carateres";
          return true;
        }
        const desde: string = this.Desde!.value;
        const hasta: string = this.Hasta!.value;
        const fechaDesde = new Date(desde);
        const fechahasta = new Date(hasta);
        const fechaHoy = new Date();
        //comprobar que sea mayor que el campo Desde
        if (fechaDesde.getTime() > fechahasta.getTime()){
          this.Hasta!.setErrors({ 'minor': true });
          this.mErrHasta = "El campo Hasta no puede contener una fecha anterior al campo desde";
          return true;
        }
        // console.log("yo introduje: 2022-12-10 (año/mes/dia)"); //rompe los huevos con el utc o gtm
        // console.log("new date: "+fechaDesde);
        // console.log("getDate: "+fechaDesde.getDate());
        // console.log("getUTCDate: "+fechaDesde.getUTCDate());
        // console.log("getMonth: "+fechaDesde.getMonth());

        //comprobar que no se ponga una fecha mayor a la del dia
        if (fechahasta.getTime() > fechaHoy.getTime()){
          this.Hasta!.setErrors({ 'manor': true });
          this.mErrHasta = "El campo Hasta no puede contener una fecha mayor a la actual";
          return true;
        }
        //comprobar con el pattern datePatter
        if (!value.match(customRegExp.datePattern)){
          this.Hasta!.setErrors({ 'pattern': true });
          this.mErrHasta = "El campo Desde contiene carecteres no soportados";
          return true;
        }
      }
    }
    return false;
  }

  onSubmit(event: Event){
    event.preventDefault();
    if (this.formProject.valid){
      this.spinner = true;
      // console.log(this.formProject.value);
      // console.log("el formulario es valido");
      if (this.project.nombre !== ''){
        //editar proyecto
        let id: any = this.formProject.get('id');
        this.service.putProject(id.value, this.formProject.value).subscribe({
          next: (result: any) => {
            // console.log("result");
            // console.log(result);
            this.mErrMessage = "";
            this.toastr.success(
              'Proyecto actualizado correctamente.',
              'Bien!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
          }, 
          error: (e: any) => {
            // console.log("error");
            console.log(e);
            this.toastr.error(
              'Error al intentar actualizar su Formación Academica.',
              'Error!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
            this.spinner = false;
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
          }, 
          complete: () => {
            this.comunicationService.actualizarProj(true);
            this.closeModal();
          }
        });
      }
      else {
        //crear proyecto
        this.formProject.patchValue({
          usuarios_id: this.usuario_id
        });
        this.service.postProject(this.formProject.value).subscribe({
          next: (result: any) => {
            // console.log("result");
            // console.log(result);
            this.mErrMessage = "";
            this.toastr.success(
              'Proyecto agregado correctamente.',
              'Bien!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
          }, 
          error: (e: any) => {
            // console.log("error");
            console.log(e);
            this.toastr.error(
              'Error al intentar agregar un proyecto.',
              'Error!',
              {
                timeOut: 3000,
                positionClass: 'toast-bottom-right'
              }
            );
            this.spinner = false;
            this.mErrMessage = e.error.mensaje || e.message;
            this.mErrStatus = e.status;
            this.mErrStatusText = e.statusText;
          }, 
          complete: () => {
            this.comunicationService.actualizarProj(true);
            this.closeModal();
          }
        });
      }
    }
    else {
      this.formProject.markAllAsTouched();
      // console.log("el formulario es invalido");
      // console.log(this.formProject.value);
      // console.log(this.formProject.errors);
      this.toastr.error(
        'Revise los campos resaltados.',
        'Error!',
        {
          timeOut: 3000,
          positionClass: 'toast-bottom-right'
        }
      );
      this.spinner = false;
      this.mErrMessage = "Revise los campos.";
    }
  }

  closeModal() {
    this.modalActive.close('Modal Closed');
  }

  dismissModal(){
    this.modalActive.dismiss('Cross click');
  }
}
