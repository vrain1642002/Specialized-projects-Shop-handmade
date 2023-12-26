import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber, 
    IsDate
} from 'class-validator';

export class RegisterDTO {
    @IsString()
    Hoten: string;

    @IsPhoneNumber()
    SDT: string;
    
    @IsString()
    @IsNotEmpty()
    Diachi: string;

    @IsString()
    @IsNotEmpty()
    Matkhau: string;

    @IsString()
    @IsNotEmpty()
    Nhaplaimatkhau: string;

    @IsDate()
    Ngaysinh: Date;

    
    Ma_Vaitro: number ;
    constructor(data: any) {
        this.Hoten = data.Hoten;
        this.SDT = data.SDT;
        this.Diachi = data.Diachi;
        this.Matkhau = data.Matkhau;
        this.Nhaplaimatkhau = data.Nhaplaimatkhau;
        this.Ngaysinh = data.Ngaysinh;
        this.Ma_Vaitro =data.MaVaitro;
    }
}