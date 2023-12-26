import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/app/environments/environment';
import { Sanpham } from 'src/app/models/Sanpham';
import { CartService } from 'src/app/services/cartservice';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  cartItems: { sanpham: Sanpham, quantity: number }[] = [];
  totalAmount: number = 0;
  cart: Map<number, number> = new Map();

  constructor(
    private cartService: CartService,
    private productService: ProductService,
    private router: Router,
  ) { }

  ngOnInit(): void {
     this.cart = this.cartService.getCart();
    //chuyen danh sach ID tu map gio hang
    const productIds = Array.from(this.cart.keys());
    //goi service de lay thong tin san pham du tren danh sach id

    this.productService.getProductsByIds(productIds).subscribe({
      next: (sanphams) => {
        this.cartItems = productIds.map((productId) => {
          
            const sanpham = sanphams.find((s) => s.ma === productId);
            if (sanpham) {

              sanpham.hinhthunho = `${environment.apiBaseUrl}/sanphams/images/${sanpham.hinhthunho}`;
            }
            return{
              sanpham:sanpham!,
              quantity:this.cart.get(productId)!
            };
          });

        },
          complete: () => {
            
           this.calculateTotal();
          },
          error: (error: any) => {
            console.error('Khong lay duoc danh sach san', error);
          }
        })
  }
  calculateTotal():void{
    this.totalAmount=this.cartItems.reduce(
      (total,item)=> total + item.sanpham.gia * item.quantity,0
    )
  }
  clear():void{
    this.cartService.clearCart();
    window.location.reload();
  }
  onProductClick(Ma: number) {
    this.router.navigate(['products', Ma]);
  }
  decreaseQuantity(index:number,id:number){
   
    if(this.cartItems[index].quantity==1)   
    {         
      this.cartItems.splice(index, 1);
    }
   else 
     this.cartItems[index].quantity--;
     this.updateCartFromCartItems();
    this.calculateTotal();  
}
increaseQuantity(index:number){
  this.cartItems[index].quantity++;
  this.updateCartFromCartItems();
  this.calculateTotal();
}
onEnterKey(index:number,quantity:number,id:number){
  
  if(quantity==0)  
  {
  
    this.cartItems.splice(index, 1);
  
  }
  else this.cartItems[index].quantity=quantity;
  this.updateCartFromCartItems();
  this.calculateTotal();
}
private updateCartFromCartItems(): void {
  this.cart.clear();
  this.cartItems.forEach((item) => {
    this.cart.set(item.sanpham.ma, item.quantity);
  });
  this.cartService.setCart(this.cart);
}
  
}
