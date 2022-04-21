import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/Module/user';
import { UserService } from 'src/app/Shared/user.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  userObj : User = {
    userId: 0,
    fullName: '',
    email: '',
    password: '',
    mobile: '',
    isEmailVerified: false,
    token: ''
  }

  registrationForm = new FormGroup({
    name : new FormControl(),
    email: new FormControl(),
    password: new FormControl(),
    mobile : new FormControl()
  });

  constructor(private fb : FormBuilder, private router : Router, private userService : UserService) { }

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      mobile: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]]
    })
  }

  userRegistration() {
    this.userObj.fullName = this.registrationForm.value.name;
    this.userObj.email = this.registrationForm.value.email;
    this.userObj.password = this.registrationForm.value.password;
    this.userObj.mobile = this.registrationForm.value.mobile;
    this.userObj.isEmailVerified = false;

    localStorage.setItem('user',JSON.stringify(this.userObj));

    this.userService.sendOtp(this.userObj.email).subscribe((res: any) => {
      this.router.navigate(['/user/verify-email']);
    }, (err : any) => {
      alert('Failed to send OTP on your email')
      this.router.navigate(['/user/login']);
    })



  }

}
