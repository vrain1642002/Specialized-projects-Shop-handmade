import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber,     
} from 'class-validator';

export class InsertProductDTO {
    @IsPhoneNumber()
    ten: string;

    gia: number;

    @IsString()
    @IsNotEmpty()
    mota: string;

    Ma_Danhmucsanpham: number;
    hinhs: File[] = [];
    
    constructor(data: any) {
        this.ten = data.ten;
        this.gia = data.gia;
        this.mota = data.mota;
        this.Ma_Danhmucsanpham = data.madanhmucsanpham;
    }
}