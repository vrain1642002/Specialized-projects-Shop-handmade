import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber,     
} from 'class-validator';

export class UpdateCategoryDTO {    
    @IsString()
    @IsNotEmpty()
    ten: string;
            
    constructor(data: any) {
        this.ten = data.ten;    
    }
}