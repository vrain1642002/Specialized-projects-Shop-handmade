import { EventEmitter, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { ProductService } from './product.service';
// npm install ngx-webstorage
import { LocalStorageDirective } from 'ngx-localstorage';
@Injectable({
   providedIn: 'root'
})
export class CartService {
   //Dung map de luu tru gio hang,key la id value la so luong
   private cart: Map<number, number> = new Map();

   //cart phu 

   
   constructor() {
      // Lấy dữ liệu giỏ hàng từ localStorage khi khởi tạo service    
      const storedCart = localStorage.getItem('cart');
      if (storedCart) {
         this.cart = new Map(JSON.parse(storedCart));
      }

  

   }

   addToCart(productID: number, quantity: number = 1): void {


      if (this.cart.has(productID)) {
         //Neu san pham da co trong gio hang,tang so luong len
         this.cart.set(productID, this.cart.get(productID)! + quantity);


      }
      //neu chua co tronggio hang thi them vao voi quantity
      else {

         this.cart.set(productID, quantity);
      }
      this.saveCartToLocalStorage();
   }

   getCart(): Map<number, number> {
      return this.cart;
   }
   //luu tru gio hang vao localStorage
   saveCartToLocalStorage(): void {
      localStorage.setItem('cart', JSON.stringify(Array.from(this.cart.entries())));

   }
   clearCart(): void {
      this.cart.clear();//xoa toan bo du lieu tronggio hang
      this.saveCartToLocalStorage();//Luu gio hang moi vao local store

   }

   setCart(cart: Map<number, number>) {
      this.cart = cart ?? new Map<number, number>();
      this.saveCartToLocalStorage();
   }

   

}