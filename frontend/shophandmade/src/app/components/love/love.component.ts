import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/app/environments/environment';
import { Sanpham } from 'src/app/models/Sanpham';
import { CartService } from 'src/app/services/cartservice';
import { LoveService } from 'src/app/services/loveservice';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-love',
  templateUrl: './love.component.html',
  styleUrls: ['./love.component.scss']
})
export class LoveComponent {
  loveItems: { sanpham: Sanpham, quantity: number }[] = [];
  totalAmount: number = 0;
  //hien thi
  showMessage = false;
  message = '';
 
  constructor(
    private cartService:CartService,
    private loveService:LoveService,
    private productService: ProductService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    const love = this.loveService.getLovet();
    //chuyen danh sach ID tu map gio hang
    const productIds = Array.from(love.keys());
    //goi service de lay thong tin san pham du tren danh sach id

    this.productService.getProductsByIds(productIds).subscribe({
      next: (sanphams) => {
        this.loveItems = productIds.map((productId) => {
          
            const sanpham = sanphams.find((s) => s.ma === productId);
            if (sanpham) {

              sanpham.hinhthunho = `${environment.apiBaseUrl}/sanphams/images/${sanpham.hinhthunho}`;
            }
            return{
              sanpham:sanpham!,
              quantity:love.get(productId)!
            };
          });

        },
          complete: () => {
            
          
          },
          error: (error: any) => {
            console.error('Khong lay duoc danh sach san', error);
          }
        })
  }

  clear():void{
    this.loveService.clearLove();
    window.location.reload();
  }
  onProductClick(Ma: number) {
    this.router.navigate(['products', Ma]);
  }
 

  addToCart(Ma: number,Ten:string): void {

    this.cartService.addToCart(Ma, 1);
    this.showMessage = true;
    this.message = "Đã thêm sản phẩm "+Ten+  " vào giỏ hàng thành công";
    setTimeout(() => {
      this.hideMessage();
    }, 4000);
   
  }
  hideMessage() {
    this.showMessage = false;
  }
  clearone(Ma:number)
  {
      this.loveService.clearone(Ma);
      window.location.reload();
  }
}
