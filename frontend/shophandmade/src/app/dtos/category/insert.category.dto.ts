import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber,     
} from 'class-validator';

export class InsertCategoryDTO {    
    @IsString()
    @IsNotEmpty()
    ten: string;
            
    constructor(data: any) {
        this.ten = data.ten;    
    }
}