import { Chitietdonhang } from "./Chitietdonhang";

export interface  Donhang {
    ma: number;
    manguoidung:number;
    hoten_Nguoinhan:string;
    sdtnguoinhan:string;
    diachinhanhang:string;
    ghichu:string;
    trangthaidh:string;
    trangthai:boolean;
    ngaydathang:Date;
    tongtien:number;
    phuongthucvanchuyen:string;
    phuongthucthanhtoan:string;
    chitietdonhangs:Chitietdonhang[]

   
}