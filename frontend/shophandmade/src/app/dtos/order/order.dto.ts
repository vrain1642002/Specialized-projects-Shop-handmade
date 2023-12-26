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

 cart_items: { product_id: number, quantity: number }[]; // Thêm cart_items để lưu thông tin giỏ hàng

  constructor(data: any) {
    this.Ma_Nguoidung= data.Ma_Nguoidung
    this.Hoten_Nguoinhan= data.Hoten_Nguoinhan
 
    // this= data.trangthaiDH;
    this.SDT_Nguoinhan=data.SDT_Nguoinhan;
    this.Ghichu=data.Ghichu;
    this.Tongtien=data.Tongtien;
    this.Phuongthucthanhtoan=data.phuongthucthanhtoan;
    this.Phuongthucvanchuyen=data.phuongthucvanchuyen;
    this.Diachigiaohang=data.Diachigiaohang;
      this.cart_items = data.cart_items;
  }
}

