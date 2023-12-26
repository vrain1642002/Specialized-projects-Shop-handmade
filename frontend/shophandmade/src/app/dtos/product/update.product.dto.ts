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

    madanhmucsanpham: number;
    
    
    constructor(data: any) {
        this.ten = data.ten;
        this.gia = data.gia;
        this.mota = data.mota;
        this.madanhmucsanpham = data.madanhmucsanpham;
    }
}