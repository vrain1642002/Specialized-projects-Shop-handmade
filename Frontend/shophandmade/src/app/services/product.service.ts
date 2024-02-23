import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import {  Sanpham } from '../models/Sanpham';
import { InsertProductDTO } from '../dtos/product/insert.product.dto';
import { UpdateProductDTO } from '../dtos/product/update.product.dto';
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiGetProducts  = `${environment.apiBaseUrl}/sanphams`;
  constructor(private http: HttpClient) { }
  private apiBaseUrl = environment.apiBaseUrl;

  //goi phuong api lay ds san pham theo phan trang bang hai pararam page,limit
  getProducts(keyword:string,Ma:number,page:number,limit:number):Observable<any> {
    const params=new HttpParams()
    .set('keyword',keyword.toString())
    .set('Ma',Ma.toString())
    .set('page',page.toString())
    .set('limit',limit.toString())
    return this.http.get<any>(this.apiGetProducts,{params});
  }
  getDetailProduct(productID:number): Observable<any>{
    return this.http.get(`${environment.apiBaseUrl}/sanphams/${productID}`)

  }
  getProductsByIds(productIds:number[]):Observable<Sanpham[]>{
    //chuyen danh sach id thanh mot chuoi va truyen vao parra,
    const params=new HttpParams().set('ids',productIds.join(','));
    return this.http.get<Sanpham[]>(`${this.apiGetProducts}/by-ids`,{params});

  }
  deleteProduct(productId: number): Observable<any> {
    debugger
    return this.http.delete<string>(`${this.apiBaseUrl}/sanphams/${productId}`);
  }
  updateProduct(productId: number, updatedProduct: UpdateProductDTO): Observable<any> {
    debugger
    return this.http.put<Sanpham>(`${this.apiBaseUrl}/sanphams/${productId}`, updatedProduct);
  }  
  insertProduct(insertProductDTO: InsertProductDTO): Observable<any> {
    // Add a new product
    debugger
    return this.http.post(`${this.apiBaseUrl}/sanphams`, insertProductDTO);
  }
uploadImages(productId: number, files: File[]): Observable<any> {

    const formData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i]);
    }
    // Upload images for the specified product id
    return this.http.post(`${this.apiBaseUrl}/sanphams/uploads/${productId}`, formData);
  }
  deleteProductImage(id: number): Observable<any> {
    debugger
    return this.http.delete<string>(`${this.apiBaseUrl}/product_images/${id}`);
  }

}
