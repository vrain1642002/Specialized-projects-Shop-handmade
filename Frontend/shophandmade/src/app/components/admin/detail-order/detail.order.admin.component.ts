import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Donhang } from 'src/app/models/Donhang';
import { OrderService } from 'src/app/services/order.service';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environments/environment';
import { OrderResponse } from 'src/app/responses/order/order.response';
import { Chitietdonhang } from 'src/app/models/Chitietdonhang';
import { OrderDTO } from 'src/app/dtos/order/order.dto';

@Component({
  selector: 'app-detail-order-admin',
  templateUrl: './detail.order.admin.component.html',
  styleUrls: ['./detail.order.admin.component.scss']
})

export class DetailOrderAdminComponent implements OnInit{    
  orderId:number = 0;
  orderResponse: OrderResponse = {
    ma: 0, // Hoặc bất kỳ giá trị số nào bạn muốn
    manguoidung: 0,
    hoten_Nguoinhan: '',
    sdtnguoinhan: '',
    diachinhanhang: '',
    ghichu: '',
    ngaydathang: new Date(),
    trangthaidh: '',
     trangthai:true,
    tongtien: 0, 
    phuongthucthanhtoan: '',
    phuongthucvanchuyen:'',
    chitietdonhangs: [],
    
  };  
  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.getOrderDetails();
  }
  
  getOrderDetails(): void {
    debugger
    this.orderId = Number(this.route.snapshot.paramMap.get('id'));
    this.orderService.getOrderById(this.orderId).subscribe({
      next: (response: any) => {        
        debugger;       
        this.orderResponse.ma = response.ma;
        this.orderResponse.manguoidung = response.manguoidung;
        this.orderResponse.hoten_Nguoinhan = response.hoten_Nguoinhan;
        this.orderResponse.sdtnguoinhan = response.sdtnguoinhan;
        this.orderResponse.diachinhanhang = response.diachinhanhang;
        this.orderResponse.ghichu = response.ghichu;
        this.orderResponse.tongtien = response.tongtien;
        if (response.order_date) {
          this.orderResponse.ngaydathang = new Date(
            response.order_date[0], 
            response.order_date[1] - 1, 
            response.order_date[2]
          );        
        }        
        this.orderResponse.chitietdonhangs = response.chitietdonhangs
        .map((Chitietdonhang: Chitietdonhang) => {
       Chitietdonhang.sanpham.hinhthunho = `${environment.apiBaseUrl}/sanphams/images/${Chitietdonhang.sanpham.hinhthunho}`;
        return Chitietdonhang;
      });              
        this.orderResponse.phuongthucthanhtoan = response.phuongthucthanhtoan;
        this.orderResponse.phuongthucvanchuyen = response.phuongthucvanchuyen;       
        this.orderResponse.trangthaidh= response.trangthaidh;     
      
      },
      complete: () => {
        debugger;        
      },
      error: (error: any) => {
        debugger;
        console.error('Error fetching detail:', error);
      }
    });
  }    
  
  saveOrder(): void {    
    debugger    
    this.orderService
      .updateOrder(this.orderId, new OrderDTO(this.orderResponse))
      .subscribe({
      next: (response: any) => {
        debugger
        // Handle the successful update
        console.log('Order updated successfully:', response);
        // Navigate back to the previous page
        this.router.navigate(['../'], { relativeTo: this.route });
      },
      complete: () => {
        debugger;        
      },
      error: (error: any) => {
        // Handle the error
        debugger
        console.error('Error updating order:', error);
      }
    });   
  }
}