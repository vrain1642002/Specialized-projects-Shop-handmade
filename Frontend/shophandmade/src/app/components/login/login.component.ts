import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { LoginDTO } from 'src/app/dtos/user/login.dto';
import { LoginResponse } from 'src/app/responses/user/login.response';
import { TokenService } from 'src/app/services/token.service';
import { CartService } from 'src/app/services/cartservice';
import { UserResponse } from 'src/app/responses/user/user.response copy';
 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm!: NgForm;
  
  SDT:string;
  Matkhau:string;
  rememberMe: boolean = true;
  showPassword: boolean = false;
  userResponse?: UserResponse

  constructor(private cartService:CartService, private router: Router, private userService: UserService,private tokenService:TokenService)
  {
   this.SDT='';
   this.Matkhau='';
   
    
  }
  login() {
    
 
  

    const loginDTO: LoginDTO = {
      SDT: this.SDT,
      Matkhau: this.Matkhau,
   
    };
    this.userService.login(loginDTO).subscribe({
      next: (response: LoginResponse) => {
      
        const { token } = response;
        if (this.rememberMe) {          
          this.tokenService.setToken(token);
        
          this.userService.getUserDetail(token).subscribe({
            next: (response: any) => {
             
              this.userResponse = {
                ...response,
                ngaysinh: new Date(response.ngaysinh),
              };    
              this.userService.saveUserResponseToLocalStorage(this.userResponse); 
              if(this.userResponse?.vaitro.ma == 1) {
                alert("Đăng nhập admin thành công");
                this.router.navigate(['admin']);    
              } else if(this.userResponse?.vaitro.ma == 2) {
                alert("Đăng nhập khách hàng thành công");
                this.router.navigate(['/']);                      
              }
              
            },
            complete: () => {
             
              debugger;
            },
            error: (error: any) => {
              debugger;
              alert(error.error.message);
            }
          })
        }                        
      },
      complete: () => {
        debugger;
      },
      error: (error: any) => {
        debugger;
        alert(error.error.message);
      }
    });
  }
    
  isPhoneNumberValid(): boolean {
    //kiem tra hop le khong
    return this.SDT.length === 10 && !isNaN(Number(this.SDT));
  
  }
  isPasswordValid(): boolean {
    let minLength = 7; // Độ dài tối thiểu của mật khẩu
    let hasUpperCase = /[A-Z]/.test(this.Matkhau); // Kiểm tra có chữ cái in hoa hay không
    let hasLowerCase = /[a-z]/.test(this.Matkhau); // Kiểm tra có chữ cái in thường hay không
    let hasDigit = /\d/.test(this.Matkhau); // Kiểm tra có chữ số hay không
    let hasSpecialChar = /[@$!%*?&]/.test(this.Matkhau); // Kiểm tra có ký tự đặc biệt hay không
  
    return (
      this.Matkhau.length >= minLength &&
      hasUpperCase &&
      hasLowerCase &&
      hasDigit &&
      hasSpecialChar
    );
  }
  togglePassword() {
    this.showPassword = !this.showPassword;
  }
}
