// home.component.ts
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  constructor(private router: Router) {}

  redirectToProduct() {
    // Chuyển hướng đến trang sản phẩm (đặt URL của trang sản phẩm ở đây)
    this.router.navigate(['/product']);
  }
  showAlert() {
    alert('Chuyển trang'); // Hiển thị hộp thoại thông báo khi được gọi
  }
}
