import { Vaitro } from "./Vaitro";

export interface Nguoidung {
    ma: number;
    hoten: string;
    sdt:string;
    matkhau:string;
    trangthai: boolean;
    ngaysinh: Date;
    diachi:string;
    vaitro: Vaitro; 
}
  