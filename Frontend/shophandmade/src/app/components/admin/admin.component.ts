import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

import { TokenService } from 'src/app/services/token.service';
import { Router } from '@angular/router';
import { UserResponse } from 'src/app/responses/user/user.response copy';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: [
    './admin.component.scss',        
  ]
})
export class AdminComponent implements OnInit {
  //adminComponent: string = 'orders';
  userResponse?:UserResponse | null;
  constructor(
    private userService: UserService,       
    private tokenService: TokenService,    
    private router: Router,
  ) {
    
   }
  ngOnInit() {
   
    // this.userResponse = this.userService.getUserResponseFromLocalStorage();    
    // Default router
    this.userResponse = this.userService.getUserResponseFromLocalStorage(); 
    if (this.userResponse?.vaitro.ma!=1)
    {
      alert("Bạn chưa đăng nhập hoặc không phải là admin");
      this.router.navigate(['/']); 
    }

   }  
  logout() {
    this.userService.removeUserFromLocalStorage();
    this.tokenService.removeToken();
    this.userResponse = this.userService.getUserResponseFromLocalStorage();    
    this.router.navigate(['/']);
  }
  showAdminComponent(componentName: string): void {
    if (componentName === 'orders') {
      this.router.navigate(['/admin/orders']);
    } else if (componentName === 'categories') {
      this.router.navigate(['/admin/categories']);
    } else if (componentName === 'products') {
      this.router.navigate(['/admin/products']);
    }
  }

}


