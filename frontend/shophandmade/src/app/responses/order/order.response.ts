import { Chitietdonhang } from "src/app/models/Chitietdonhang";
export interface OrderResponse {
    ma: number;
    manguoidung: number;
    hoten_Nguoinhan: string;
    sdtnguoinhan:string;
    diachinhanhang: string;
    ghichu: string;
    ngaydathang: Date; // Dạng chuỗi ISO 8601
    trangthaidh: string;
    tongtien: number;
    phuongthucthanhtoan: string;
    trangthai:boolean;
    phuongthucvanchuyen: string;
    chitietdonhangs: Chitietdonhang[]; // Đảm bảo có một interface OrderDetail tương ứng
  }
  
  