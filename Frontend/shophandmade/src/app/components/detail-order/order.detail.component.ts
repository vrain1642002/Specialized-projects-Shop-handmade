import { Component, OnInit } from '@angular/core';

import { ProductService } from '../../services/product.service';
import { OrderService } from '../../services/order.service';

import { OrderDTO } from '../../dtos/order/order.dto';
import { OrderResponse } from 'src/app/responses/order/order.response';

import { ActivatedRoute, Router } from '@angular/router';
import { Chitietdonhang } from 'src/app/models/Chitietdonhang';
import { environment } from 'src/app/environments/environment';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order.detail.component.html',
  styleUrls: ['./order.detail.component.scss']
})
export class OrderDetailComponent implements OnInit {  
  orderResponse: OrderResponse = {
    ma: 0,
    manguoidung:0,
    hoten_Nguoinhan: "",
    sdtnguoinhan: "", 
    diachinhanhang: "",
    ghichu: "",
    ngaydathang: new Date(),
    trangthaidh: "",
    tongtien: 0, 
    phuongthucvanchuyen: "",
     phuongthucthanhtoan: "",
    trangthai: true,
    chitietdonhangs:[]
  };  
  constructor(
    private orderService: OrderService,
    private activateroute: ActivatedRoute,
    private router: Router,
    
    ) {}

  ngOnInit(): void {
    this.getOrderDetails();
  }
  
  getOrderDetails(): void {
   
    const orderId =    Number(this.activateroute.snapshot.paramMap.get('id'));
  

    this.orderService.getOrderById(orderId).subscribe({
      next: (response: any) => {        
        this.orderResponse.ma=response.ma;
         this.orderResponse.manguoidung = response.manguoidung;
        this.orderResponse.hoten_Nguoinhan= response.hoten_Nguoinhan;
     
        this.orderResponse.sdtnguoinhan = response.sdtnguoinhan;
        this.orderResponse.diachinhanhang = response.diachinhanhang;
        this.orderResponse.ghichu = response.ghichu;
          
        
        this.orderResponse.chitietdonhangs = response.chitietdonhangs
          .map((Chitietdonhang: Chitietdonhang) => {
         Chitietdonhang.sanpham.hinhthunho = `${environment.apiBaseUrl}/sanphams/images/${Chitietdonhang.sanpham.hinhthunho}`;
          return Chitietdonhang;
        });        
        this.orderResponse.phuongthucvanchuyen = response.phuongthucvanchuyen;
       
        this.orderResponse.trangthaidh=response.trangthaidh;
        this.orderResponse.phuongthucthanhtoan= response.phuongthucthanhtoan;
        this.orderResponse.ngaydathang=response.ngaydathang;
     
        this.orderResponse.tongtien = response.tongtien;
      },
      complete: () => {
        debugger;        
      },
      error: (error: any) => {
        debugger;
 
       
        if(this.orderResponse.ma==0)
        {
          alert("Không tìm thấy đơn hàng vừa nhập,vui lòng kiểm tral lại");
          this.refind();
        }
      }
    });
  }
  onProductClick(Ma: number) {
    this.router.navigate(['products', Ma]);
  }
  refind() {
    this.router.navigate(['find']);
  }
}

