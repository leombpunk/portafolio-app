import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http'
import { NgbModule, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { interceptorProvider } from './services/interceptor.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { SectionsComponent } from './components/sections/sections.component';
import { CardBiographyComponent } from './components/card-biography/card-biography.component';
import { CardProjectsComponent } from './components/card-projects/card-projects.component';
import { CardSkillsComponent } from './components/card-skills/card-skills.component';
import { CardDefaultComponent } from './components/card-default/card-default.component';
import { FooterComponent } from './components/footer/footer.component';
import { ButtonAddComponent } from './components/button-add/button-add.component';
import { ButtonEditComponent } from './components/button-edit/button-edit.component';
import { ButtonDeleteComponent } from './components/button-delete/button-delete.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ModalEditImageComponent } from './components/modal-edit-image/modal-edit-image.component';
import { ModalDeleteSomeComponent } from './components/modal-delete-some/modal-delete-some.component';
import { ModalAddEditBiographyComponent } from './components/modal-add-edit-biography/modal-add-edit-biography.component';
import { ModalAddEditExperienceComponent } from './components/modal-add-edit-experience/modal-add-edit-experience.component';
import { ModalAddEditAcademicComponent } from './components/modal-add-edit-academic/modal-add-edit-academic.component';
import { ModalAddEditSkillComponent } from './components/modal-add-edit-skill/modal-add-edit-skill.component';
import { ModalAddEditProjectComponent } from './components/modal-add-edit-project/modal-add-edit-project.component';
import { HomeComponent } from './components/home/home.component';
import { RegistroComponent } from './components/registro/registro.component';
import { AlertDangerComponent } from './components/alert-danger/alert-danger.component';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { CardEmptyComponent } from './components/card-empty/card-empty.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    SectionsComponent,
    CardBiographyComponent,
    CardProjectsComponent,
    CardSkillsComponent,
    CardDefaultComponent,
    FooterComponent,
    ButtonAddComponent,
    ButtonEditComponent,
    ButtonDeleteComponent,
    ModalEditImageComponent,
    ModalDeleteSomeComponent,
    ModalAddEditBiographyComponent,
    ModalAddEditExperienceComponent,
    ModalAddEditAcademicComponent,
    ModalAddEditSkillComponent,
    ModalAddEditProjectComponent,
    HomeComponent,
    RegistroComponent,
    AlertDangerComponent,
    SpinnerComponent,
    CardEmptyComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    ReactiveFormsModule, 
    HttpClientModule, 
    FontAwesomeModule,
    NgbModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      preventDuplicates: true,
      closeButton: true
    }),
  ],
  providers: [NgbActiveModal, interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
