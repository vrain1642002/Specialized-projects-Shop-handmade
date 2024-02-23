import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber, 
    IsDate
} from 'class-validator';

export class LoginDTO {
    @IsPhoneNumber()
    SDT: string;

    @IsString()
    @IsNotEmpty()
    Matkhau: string;

    constructor(data: any) {
        this.SDT = data.SDT;
        this.Matkhau = data.Matkhau;
       
    }

}