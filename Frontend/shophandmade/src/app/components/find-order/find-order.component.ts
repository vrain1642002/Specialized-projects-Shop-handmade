import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-find-order',
  templateUrl: './find-order.component.html',
  styleUrls: ['./find-order.component.scss']
})
export class FindOrderComponent {
  inputValue: number | undefined;
  constructor(private router: Router) {}

  submitForm() {
 
    // Kiểm tra xem inputValue có giá trị hay không
    if (this.inputValue !== undefined) {
      if(this.inputValue <1)
      {
      alert("Mã số đơn hàng không hợp lệ");
      return;
      }
       // Chuyển đến trang khác
      this.router.navigate(['/order', this.inputValue]);
    } else {
      console.error('Input value is undefined or not a number.');
    }
  }

}
