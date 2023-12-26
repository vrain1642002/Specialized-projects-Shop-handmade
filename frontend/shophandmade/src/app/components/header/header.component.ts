import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { TokenService } from 'src/app/services/token.service';
import { Router } from '@angular/router';
import { UserResponse } from 'src/app/responses/user/user.response copy';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{
  userResponse?:UserResponse | null;


  constructor(
    private userService: UserService,       
    private tokenService: TokenService,    
    private router: Router,
  ) {
    
   }
  ngOnInit() {
    this.userResponse = this.userService.getUserResponseFromLocalStorage();    
  }  
  logout() {
    this.userService.removeUserFromLocalStorage();
    this.tokenService.removeToken();
    this.userResponse = this.userService.getUserResponseFromLocalStorage();    
    this.router.navigate(['/']);
  }


  toggleResponsiveClass() {
    const x = document.getElementById("myTopnav");

    if (x) {
      x.classList.toggle("responsive");
      }
    }
  

}
