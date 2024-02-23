export class UpdateUserDTO {
    hoten: string;    
    diachi: string;    
    matkhau: string;    
    nhaplaimatkhau: string;    
    ngaysinh: Date;    
    
    constructor(data: any) {
        this.hoten = data.hoten;
        this.diachi = data.diachi;
        this.matkhau= data.matkhau;
        this.nhaplaimatkhau = data.nhaplaimatkhau
        this.ngaysinh= data.ngaysinh      
    }
}