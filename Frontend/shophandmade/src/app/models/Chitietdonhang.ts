import { Sanpham } from "./Sanpham";
import { Donhang} from "./Donhang";




export interface  Chitietdonhang {
    ma: number;
    donghang:Donhang ;
    sanpham: Sanpham;
    gia: number;
    soluong: number;
    thanhtien: number;
   
}