import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { RegisterDTO } from 'src/app/dtos/user/register.dto';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  //bien tham chieu den form
  @ViewChild('registerForm') registerForm!:NgForm;
 //khai bao cac bien voi truong du lieu trong form
 SDT:string;
 Matkhau:string;
 Nhaplaimatkhau:string;
 Hoten:string;
 Diachi:string;
 isAccpeted:boolean;
 Ngaysinh:Date;
 MaVaitro:number;
 showPassword: boolean = false;


 constructor(private router: Router, private userService: UserService)
 {
  this.SDT='';
  this.Matkhau='';
  this.Nhaplaimatkhau='';
  this.Hoten='';
  this.Diachi='';
  this.isAccpeted=false;
  this.Ngaysinh=new Date();
  this.Ngaysinh.setFullYear(this.Ngaysinh.getFullYear()-18);
  this.MaVaitro=2;

 }
 onPhoneChange(){
  console.log(`Phone : ${this.SDT}`)

}
register() {

  const RegisterDTO:RegisterDTO = {
      "Hoten": this.Hoten,
      "SDT": this.SDT,
      "Diachi": this.Diachi,
      "Matkhau": this.Matkhau,
      "Nhaplaimatkhau": this.Nhaplaimatkhau,
      "Ngaysinh": this.Ngaysinh,
      "Ma_Vaitro": 2,
  }
  this.userService.register(RegisterDTO).subscribe({
    next: (response: any) => {
      const confirmation = window
            .confirm('Đăng ký thành công, mời bạn đăng nhập. Bấm "OK" để chuyển đến trang đăng nhập.');
          if (confirmation) {
            this.router.navigate(['/login']);
          }
       
    },
    complete: () => {
      
    },
    error: (error: any) => {      
            // alert(`Cannot register, error: ${error.error}`)  
    alert(`Đăng kí tài khoản thất bại vì sdt đã được sử dụng`) ;      
    }
  })   
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
checkPasswordsMatch() {    
  if (this.Matkhau !== this.Nhaplaimatkhau) {
    this.registerForm.form.controls['retypePassword']
          .setErrors({ 'passwordMismatch': true });
  } else {
    this.registerForm.form.controls['retypePassword'].setErrors(null);
  }
}
togglePassword() {
  this.showPassword = !this.showPassword;
}
}


