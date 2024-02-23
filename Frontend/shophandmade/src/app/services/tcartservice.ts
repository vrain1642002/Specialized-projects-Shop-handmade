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
export class CartServiceT {
   //Dung map de luu tru gio hang,key la id value la so luong
   private tcart: Map<number, number> = new Map();

   //cart phu 

   
   constructor() {
      // Lấy dữ liệu giỏ hàng từ localStorage khi khởi tạo service    
      const tstoredCart = localStorage.getItem('tcart');
      if (tstoredCart) {
         this.tcart = new Map(JSON.parse(tstoredCart));
      }

  

   }

   addToCart(productID: number, quantity: number = 1): void {


        this.clearCart();
         this.tcart.set(productID, quantity);
   
      this.saveCartToLocalStorage();
   }

   getCart(): Map<number, number> {
      return this.tcart;
   }
   //luu tru gio hang vao localStorage
   saveCartToLocalStorage(): void {
      localStorage.setItem('tcart', JSON.stringify(Array.from(this.tcart.entries())));

   }
   clearCart(): void {
      this.tcart.clear();//xoa toan bo du lieu tronggio hang
      this.saveCartToLocalStorage();//Luu gio hang moi vao local store

   }

   setCart(tcart: Map<number, number>) {
      this.tcart = tcart ?? new Map<number, number>();
      this.saveCartToLocalStorage();
   }

   

}