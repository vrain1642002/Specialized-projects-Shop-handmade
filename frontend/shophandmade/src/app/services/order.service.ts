import { ProductService } from './product.service';
import { Injectable } from '@angular/core';
import { 
  HttpClient, 
  HttpParams, 
  HttpHeaders 
} from '@angular/common/http';
import { Observable } from 'rxjs';

import { OrderDTO } from '../dtos/order/order.dto';
import { environment } from '../environments/environment';
import { OrderResponse } from '../responses/order/order.response';



@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private apiUrl = `${environment.apiBaseUrl}/donhangs`;
  private apiGetAllOrders = `${environment.apiBaseUrl}/donhangs/get-orders-by-keyword`;

  constructor(private http: HttpClient) {}

  placeOrder(orderData: OrderDTO): Observable<any> {    
    // Gửi yêu cầu đặt hàng
    return this.http.post(this.apiUrl, orderData);
  }
  getOrderById(orderId: number): Observable<any> {
    const url = `${environment.apiBaseUrl}/donhangs/${orderId}`;
    return this.http.get(url);
  }
  getAllOrders(keyword:string,
    page: number, limit: number
  ): Observable<OrderResponse[]> {
      const params = new HttpParams()
      .set('keyword', keyword)      
      .set('page', page.toString())
      .set('limit', limit.toString());            
      return this.http.get<any>(this.apiGetAllOrders, { params });
  }
  updateOrder(orderId: number, orderData: OrderDTO): Observable<any> {
    const url = `${environment.apiBaseUrl}/donhangs/${orderId}`;
    return this.http.put(url, orderData);
  }
  deleteOrder(orderId: number): Observable<any> {
    const url = `${environment.apiBaseUrl}/donhangs/${orderId}`;
    return this.http.delete(url, { responseType: 'text' });
  }
}
