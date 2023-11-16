import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonsComponent } from './buttons/buttons.component';
import { HeaderComponent } from './header/header.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { WelcomeContentComponent } from './welcome-content/welcome-content.component';
import { AuthContentComponent } from './auth-content/auth-content.component';
import { ContentComponent } from './content/content.component';

import { AxiosService } from './axios.service';
import { GroupsComponent } from './components/groups/groups.component';
import {HttpClientModule} from "@angular/common/http";
import { PermissionsComponent } from './components/permissions/permissions.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { UsersComponent } from './components/users/users.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MultiSelectModule} from "primeng/multiselect";
import {DialogModule} from "primeng/dialog";

@NgModule({
  declarations: [
    AppComponent,
    ButtonsComponent,
    HeaderComponent,
    LoginFormComponent,
    WelcomeContentComponent,
    AuthContentComponent,
    ContentComponent,
    GroupsComponent,
    PermissionsComponent,
    CategoriesComponent,
    UsersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MultiSelectModule,
    DialogModule
  ],
  providers: [AxiosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
