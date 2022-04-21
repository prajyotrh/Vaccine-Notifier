import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from 'src/app/Module/location';
import { AdminService } from 'src/app/Shared/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  VaccineFormData = new FormGroup({
    locationId: new FormControl(),
    locationName: new FormControl(),
    vaccineCount: new FormControl(),
    date: new FormControl(),
    vaccineName: new FormControl(),
    vaccineType: new FormControl(),
  });

  locationObj: Location = {
    vaccineName: '',
    locationId: 0,
    locationName: '',
    vaccineCount: 0,
    date: '',
    vaccineType: ''
  }

  vaccines: any = ['covishield', 'covaxin', 'sputnik'];
  types: any = ['free', 'paid'];
  locations: Location[] = [];

  constructor(private router: Router, private fb: FormBuilder, private adminService: AdminService) { }

  ngOnInit(): void {
    this.VaccineFormData = this.fb.group({
      locationName: ['', [Validators.required]],
      vaccineCount: ['', [Validators.required]],
      date: ['', [Validators.required]],
      vaccineName: ['', [Validators.required]],
      vaccineType: ['', [Validators.required]]
    });
    this.getAllLocations();
  }

  addCenter() {
    this.locationObj.locationId = 0;
    this.locationObj.locationName = this.VaccineFormData.value.locationName;
    this.locationObj.vaccineName = this.VaccineFormData.value.vaccineName;
    this.locationObj.vaccineCount = this.VaccineFormData.value.vaccineCount;
    this.locationObj.vaccineType = this.VaccineFormData.value.vaccineType;
    this.locationObj.date = this.VaccineFormData.value.date;

    this.adminService.addVaccineCenter(this.locationObj).subscribe(res => {
      this.ngOnInit();
    }, err => {
      console.log(err);
    })

  }

  getAllLocations() {
    this.adminService.getAllLocations().subscribe(res => {
      this.locations = res;
    }, err => {
      console.log(err);
    })
  }


  deleteCenter(location: Location) {

    if (window.confirm("Are you sure you want to delete this location ? " + location.locationName)) {
      this.adminService.deleteLocation(location).subscribe(res => {
        this.ngOnInit();
      }, err => {
        console.log(err);
      });
    }

  }

  getVaccineCenter(location: Location) {



    this.VaccineFormData = this.fb.group({
      vaccineName: location.vaccineName,
      locationId: location.locationId,
      locationName: location.locationName,
      vaccineCount: location.vaccineCount,
      date: location.date.substring(0,location.date.indexOf('T')),
      vaccineType: location.vaccineType
    });


    console.log(this.VaccineFormData.value);
  }

  updateVaccineCenter() {

    this.locationObj.locationId = this.VaccineFormData.value.locationId;
    this.locationObj.locationName = this.VaccineFormData.value.locationName;
    this.locationObj.vaccineName = this.VaccineFormData.value.vaccineName;
    this.locationObj.vaccineCount = this.VaccineFormData.value.vaccineCount;
    this.locationObj.vaccineType = this.VaccineFormData.value.vaccineType;
    this.locationObj.date = this.VaccineFormData.value.date;

    this.adminService.updateLocation(this.locationObj).subscribe(res => {
      let element: HTMLElement = document.getElementById('closeModal') as HTMLElement;
      element.click();
      this.ngOnInit();
    }, err => {
      console.log('Error while updating faculty ' + err);
    })
  }


  signout() {
    localStorage.removeItem("token");
    this.router.navigate(['/admin']);
  }

}

