import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/Module/user';
import { UserService } from 'src/app/Shared/user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  });

  userObj : User = {
    userId: 0,
    fullName: '',
    email: '',
    password: '',
    mobile: '',
    isEmailVerified: true,
    token: ''
  }

  constructor(private fb : FormBuilder, private userService : UserService, private router : Router) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required,Validators.email]],
      password: ['', [Validators.required]],
    })
  }

  userLogin() {
    this.userObj.email = this.loginForm.value.username;
    this.userObj.password = this.loginForm.value.password;

    this.userService.loginUser(this.userObj).subscribe(res => {
      if(res != null) {
        localStorage.setItem('user',JSON.stringify(res));
      this.router.navigate(['/user/dashboard']);
      } else {
        alert('Login failed. Please enter valid credentials.');
        this.router.navigate(['/']);
      }
    }, err => {
      alert('Login failed');
      this.router.navigate(['/']);
    })

  }

}
