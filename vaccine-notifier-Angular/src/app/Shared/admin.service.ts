import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Location } from '../Module/location';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  API_URL : string = "http://localhost:9092/vaccine-notifier-admin";

  headerDict = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
  }

  requestOptions = {
    headers: new HttpHeaders(this.headerDict),
  };

  constructor(private router : Router, private http : HttpClient) { }

  adminLogin(email : string, password : string) {
    if(email === "admin" && password === "Admin@123") {
      this.router.navigate(['/admin/dashboard']);
      localStorage.setItem("token", Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15));
    } else {
      alert("Please enter valid credentials.")
      this.router.navigate(['/admin/']);
    }
  }

  gettoken() {
    return !!localStorage.getItem("token");
  }

  addVaccineCenter(locationObj: Location) : Observable<Location>{
    return this.http.post<Location>(this.API_URL+'/add-location',locationObj, this.requestOptions);
  }

  getAllLocations() : Observable<Location[]> {
    return this.http.get<Location[]>(this.API_URL+'/all-locations');
  }

  deleteLocation(location : Location) {
    return this.http.delete(this.API_URL+'/delete-location/'+location.locationId);
  }

  updateLocation(locationObj: Location) : Observable<Location> {
    return this.http.put<Location>(this.API_URL+'/update-location/'+locationObj.locationId,locationObj);
  }

}
