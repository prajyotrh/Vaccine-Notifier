import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Alert } from 'src/app/Module/alert';
import { Location } from 'src/app/Module/location';
import { AdminService } from 'src/app/Shared/admin.service';
import { UserService } from 'src/app/Shared/user.service';

export interface CenterInfo {
  locationId: number,
  locationName: string
}

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent implements OnInit {

  AlertFormData = new FormGroup({
    locationId: new FormControl(),
    vaccineName: new FormControl(),
    vaccineType: new FormControl(),
  });

  locations: Location[] = [];
  locationName: string = '';
  allAlerts: Alert[] = [];

  vaccineCenters: CenterInfo[] = [];
  vaccines: any = ['covishield', 'covaxin', 'sputnik'];
  types: any = ['free', 'paid'];

  vaccineCenterObj: CenterInfo = {
    locationId: 0,
    locationName: ''
  }

  alertObj: Alert = {
    alertId: 0,
    locationId: 0,
    email: '',
    vaccineName: '',
    vaccineType: ''
  }

  user: string = '';
  email: string = '';
  token: string = '';

  constructor(private userService: UserService, private adminService: AdminService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.user = localStorage.getItem("user")!;
    this.email = JSON.parse(this.user).email;
    this.token = JSON.parse(this.user).token;
    this.getAllAlerts();
    this.getAllLocations();
  }

  getAllAlerts() {
    this.userService.getAllAlerts(this.email, this.token).subscribe(res => {
      console.log(res);
      this.allAlerts = res;
    }, err => {
      console.log(err);
    })
  }

  deleteAlert(alertObj: Alert) {
    if (window.confirm('Are you sure, you want to delete this ' + alertObj.alertId + ' ?')) {
      this.userService.deleteAlert(this.token, alertObj.alertId).subscribe(res => {
        if (res === true) {
          this.ngOnInit();
        } else {
          alert('Failed to delete this alert');
        }
      }, err => {
        console.log(err);
      })
    }
  }

  getAllLocations() {
    this.adminService.getAllLocations().subscribe(res => {
      this.locations = res;
    }, err => {
      console.log(err);
    })
  }


  addAlert() {
    this.alertObj.alertId = 0;
    this.alertObj.locationId = this.AlertFormData.value.locationId;
    this.alertObj.vaccineName = this.AlertFormData.value.vaccineName;
    this.alertObj.vaccineType = this.AlertFormData.value.vaccineType;
    this.alertObj.email = this.email;

    this.userService.addAlert(this.alertObj, this.token).subscribe(res => {
      this.getAllAlerts();
    }, err => {
      console.log(err);
    })
  }

  getLocationName(locationId: number): string {
    this.locations.forEach(element => {
      if (element.locationId === locationId) {
        this.locationName = element.locationName;
      }
    });
    return this.locationName;
  }

}
