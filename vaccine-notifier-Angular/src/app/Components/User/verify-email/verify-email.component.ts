import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/Module/user';
import { UserService } from 'src/app/Shared/user.service';

@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.css']
})
export class VerifyEmailComponent implements OnInit {

  userObj: User = {
    userId: 0,
    fullName: '',
    email: '',
    password: '',
    mobile: '',
    isEmailVerified: false,
    token: ''
  }

  user: string = '';

  otpForm = new FormGroup({
    otp: new FormControl()
  });

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.user = localStorage.getItem('user')!;
    this.userObj = JSON.parse(this.user);
  }

  verifyEmail() {
    this.userService.verifyOtp(this.userObj.email, this.otpForm.value.otp).subscribe((res: any) => {
      if (res == true) {
        this.userObj.isEmailVerified = true;
        this.userService.registerUser(this.userObj).subscribe(res => {
          localStorage.clear();
         this.router.navigate(['/user/login']);

        }, err => {

          alert('Error while adding user. Please try again');
          this.router.navigate(['user/register']);

        })

      } else {

        alert('Please enter valid otp');
        this.otpForm.setValue({ otp: '' });

      }
    }, (err: any) => {
      alert('Error while sending otp. Please try again');
      this.router.navigate(['user/register']);
    })
  }

}
