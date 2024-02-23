import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber,     
} from 'class-validator';

export class UpdateProductDTO {
    @IsPhoneNumber()
    ten: string;

    gia: number;

    @IsString()
    @IsNotEmpty()
    mota: string;

    Ma_Danhmucsanpham: string;
    hinhthunho: string;
   
    constructor(data: any) {
        this.ten = data.ten;
        this.gia = data.gia;
        this.mota = data.mota;
        this.Ma_Danhmucsanpham = data.madanhmucsanpham;
        this.hinhthunho=data.hinhthunho;
       
    }
}