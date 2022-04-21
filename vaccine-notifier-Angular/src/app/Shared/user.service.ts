import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { User } from '../Module/user';
import { Alert } from '../Module/alert';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  SERVICE_API = "http://localhost:9092/user";

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

  constructor(private http: HttpClient) { }

  sendOtp(email: string): any {
    return this.http.get<any>(this.SERVICE_API + '/send-otp/' + email, this.requestOptions);
  }

  verifyOtp(email: string, otp: number): any {
    return this.http.get<any>(this.SERVICE_API + '/verify-otp/' + email + '/' + otp, this.requestOptions);
  }

  loginUser(userObj: User): Observable<User> {
    return this.http.post<User>(this.SERVICE_API + '/user-login',userObj);
  }

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(this.SERVICE_API + '/add-user', user, this.requestOptions);
  }

  gettoken() {
    return !!localStorage.getItem("user");
  }

  getAllAlerts(email : string, token : string) : Observable<Alert[]> {
    return this.http.get<Alert[]>(this.SERVICE_API+'/all-alerts/'+email +'/'+token);
  }

  deleteAlert(token : string, alertId : number) : Observable<boolean> {
    return this.http.delete<boolean>(this.SERVICE_API+'/delete-alert/'+token+'/'+alertId);
  }

  addAlert(alertObj : Alert, token : string) : Observable<Alert> {
    return this.http.post<Alert>(this.SERVICE_API+'/add-alert/'+token,alertObj);
  }

  logoutUser(username : string) {
    localStorage.clear();
    return this.http.post<any>(this.SERVICE_API+'/user-logout',{"email" : username});
  }


}
