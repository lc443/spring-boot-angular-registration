import { Component, OnInit } from '@angular/core';;
import { NgForm } from '@angular/forms';
import {Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
user = new User();
message: string;

  constructor(private router: Router, private userService: UserService) { }
  

  ngOnInit(): void {
  }

  login() {
    this.userService.loginUser(this.user).subscribe(
      data =>  {console.log("response received"); 
      localStorage.setItem('currentUser', JSON.stringify(this.user));
    },
  
      error => { 
  
        this.message ='username or password is incorrect... try again.';
        console.log(error)
      }, 
    
      
    );
  }


 
}
