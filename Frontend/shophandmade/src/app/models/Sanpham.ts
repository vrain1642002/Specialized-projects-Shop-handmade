import { Sanphamhinh } from "./Sanphamhinh";

export interface Sanpham{
  
    ma:number;
    ten:string;
    gia:number;
    hinhthunho:string;
    mota:string;
    madanhmucsanpham:string;
    diachihinh:string;
    sanpham_hinhs:Sanphamhinh[];
    
    //day la doi tuong tra ve tu back end 

}