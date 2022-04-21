import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from 'src/app/Module/location';
import { AdminService } from 'src/app/Shared/admin.service';
import { UserService } from 'src/app/Shared/user.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  alerts : boolean = false;
  overview : boolean = false;
  user : string = '';
  username : string = '';
  locations : Location[] = [];

  constructor(private adminService : AdminService,private userService : UserService,private router : Router) { }

  ngOnInit(): void {
    this.showOverview();
    this.getAllLocations();
    this.user = localStorage.getItem('user')!;
    this.username = JSON.parse(this.user).fullName;
  }

  setOff() {
    this.overview = false;
    this.alerts = false;
  }

  showAlerts() {
    this.setOff();
    this.alerts = true;
  }

  showOverview() {
    this.setOff();
    this.overview = true;
  }

  getAllLocations() {
    this.adminService.getAllLocations().subscribe(res => {
      this.locations = res;
    }, err => {
      console.log(err);
    })
  }

  signout() {
    this.userService.logoutUser(this.username).subscribe(res => {
      this.router.navigate(['/user/login'])
    }, err => {
      this.router.navigate(['/user/login'])
    })
  }

}
