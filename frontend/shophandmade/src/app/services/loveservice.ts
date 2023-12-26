import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { ProductService } from './product.service';
// npm install ngx-webstorage
import { LocalStorageDirective } from 'ngx-localstorage';
@Injectable({
    providedIn: 'root'
})
export class LoveService {
    //Dung map de luu tru gio hang,key la id value la so luong
    private love: Map<number, number> = new Map();
    constructor(private productService: ProductService) {
        //lay du lieu gio hang tu localStorage khi khoi tao service
        const storeLove = localStorage.getItem('love');
        if (storeLove) {
            this.love = new Map(JSON.parse(storeLove));
        }
    }
    addToLove(productID: number, quantity: number = 1): void {
        this.love.set(productID, 1);
        this.saveLoveToLocalStorage();
    }
    getLovet(): Map<number, number> {
        return this.love;
    }
    //luu tru gio hang vao localStorage
    private saveLoveToLocalStorage(): void {
        localStorage.setItem('love', JSON.stringify(Array.from(this.love.entries())));

    }
    clearLove(): void {
        this.love.clear();//xoa toan bo du lieu tronggio hang
        this.saveLoveToLocalStorage();//Luu gio hang moi vao local store

    }
    clearone(productID:number):void{
        this.love.delete(productID);
        this.saveLoveToLocalStorage();
    }
}