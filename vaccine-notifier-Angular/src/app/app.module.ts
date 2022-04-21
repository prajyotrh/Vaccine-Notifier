import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './Components/Admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './Components/User/user-dashboard/user-dashboard.component';
import { LoginComponent } from './Components/Admin/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserLoginComponent } from './Components/User/user-login/user-login.component';
import { UserRegistrationComponent } from './Components/User/user-registration/user-registration.component';
import { VerifyEmailComponent } from './Components/User/verify-email/verify-email.component';
import { AlertsComponent } from './Components/User/user-dashboard/alerts/alerts.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    UserDashboardComponent,
    LoginComponent,
    UserLoginComponent,
    UserRegistrationComponent,
    VerifyEmailComponent,
    AlertsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
