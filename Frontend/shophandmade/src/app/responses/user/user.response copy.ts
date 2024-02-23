import { Vaitro } from "src/app/models/Vaitro";


export interface UserResponse {
    ma: number;
    hoten: string;
    sdt:string;
    matkhau:string;
    trangthai: boolean;
    ngaysinh: Date;
    diachi:string;
    vaitro: Vaitro; 
}
  