import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './Components/Admin/dashboard/dashboard.component';
import { LoginComponent } from './Components/Admin/login/login.component';
import { UserDashboardComponent } from './Components/User/user-dashboard/user-dashboard.component';
import { UserLoginComponent } from './Components/User/user-login/user-login.component';
import { UserRegistrationComponent } from './Components/User/user-registration/user-registration.component';
import { VerifyEmailComponent } from './Components/User/verify-email/verify-email.component';
import { AdminAuthGuard } from './Shared/admin-auth.guard';
import { AuthGuard } from './Shared/auth.guard';

const routes: Routes = [
  {path :'admin/dashboard', component: DashboardComponent, canActivate : [AdminAuthGuard]},
  {path :'admin/login', component: LoginComponent},
  {path :'admin', redirectTo:'admin/login', pathMatch:'full'},


  {path : 'user/login', component: UserLoginComponent},
  {path : 'user/register', component: UserRegistrationComponent},
  {path : 'user/dashboard', component: UserDashboardComponent, canActivate: [AuthGuard]},
  {path : 'user/verify-email', component: VerifyEmailComponent},
  {path :'', redirectTo:'user/login', pathMatch:'full'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
