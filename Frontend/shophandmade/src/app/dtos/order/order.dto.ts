import { IsString, 
  IsNotEmpty, 
  IsPhoneNumber, 
  IsNumber, ArrayMinSize, 
  ValidateNested, 
  Length 
} from 'class-validator';
import { Type } from 'class-transformer';
import { CartItemDTO } from './cart.item.dto';

export class OrderDTO {
  //thuoc tinh gui di phai giong post
  Ma_Nguoidung: number;
  Hoten_Nguoinhan: string;
  SDT_Nguoinhan: string;
  Ghichu: string;
  Tongtien?: number;
  Phuongthucvanchuyen: string;
  Diachigiaohang: string;
   Phuongthucthanhtoan: string;
   trangthaiDH:string;
   trangthai:boolean;
 cart_items: { product_id: number, quantity: number }[]; // Thêm cart_items để lưu thông tin giỏ hàng

  constructor(data: any) {
    this.Ma_Nguoidung= data.manguoidung
    this.Hoten_Nguoinhan= data.hoten_Nguoinhan
 
    // this= data.trangthaiDH;
    this.SDT_Nguoinhan=data.sdtnguoinhan;
    this.Ghichu=data.ghichu;
    this.Tongtien=data.tongtien;
    this.trangthai=true;
    this.trangthaiDH=data.trangthaidh;
    this.Phuongthucthanhtoan=data.phuongthucthanhtoan;
    this.Phuongthucvanchuyen=data.phuongthucvanchuyen;
    this.Diachigiaohang=data.diachinhanhang;
      this.cart_items = data.cart_items;
  }
}

