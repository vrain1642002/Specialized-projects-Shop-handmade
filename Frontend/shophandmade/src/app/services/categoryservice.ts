import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import {  Danhmucsanpham } from '../models/Danhmucsanpham';
import { UpdateCategoryDTO } from '../dtos/category/update.category.dto';
import { InsertCategoryDTO } from '../dtos/category/insert.category.dto';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiBaseUrl = environment.apiBaseUrl;

  private apiGetProducts  = `${environment.apiBaseUrl}/danhmucsanphams`;
  constructor(private http: HttpClient) { }

  //goi phuong api lay ds san pham theo phan trang bang hai pararam page,limit
  getCategories(page:number,limit:number):Observable<any> {
    const params=new HttpParams()
    .set('page',page.toString())
    .set('limit',limit.toString())
    return this.http.get<any>(this.apiGetProducts,{params});
  }
  getDetailCategory(id: number): Observable<Danhmucsanpham> {
    return this.http.get<Danhmucsanpham>(`${this.apiBaseUrl}/danhmucsanphams/${id}`);
  }
  deleteCategory(id: number): Observable<string> {
    debugger
    return this.http.delete<string>(`${this.apiBaseUrl}/danhmucsanphams/${id}`);
  }
  updateCategory(id: number, updatedCategory: UpdateCategoryDTO): Observable<any> {
    debugger
    return this.http.put<Danhmucsanpham>(`${this.apiBaseUrl}/danhmucsanphams/${id}`, updatedCategory);
  }  
  insertCategory(insertCategoryDTO: InsertCategoryDTO): Observable<String> {
    // Add a new category
    return this.http.post<string>(`${this.apiBaseUrl}/danhmucsanphams`, insertCategoryDTO);
  }
}
