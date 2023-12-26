import { Component, OnInit } from '@angular/core';
import { environment } from 'src/app/environments/environment';
import { Sanpham } from 'src/app/models/Sanpham';
import { Sanphamhinh } from 'src/app/models/Sanphamhinh';
import { CartService } from 'src/app/services/cartservice';
import { ProductService } from 'src/app/services/product.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { LoveService } from 'src/app/services/loveservice';
import { CartServiceT } from 'src/app/services/tcartservice';
// npm install ngx-webstorage


@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.scss']
})
export class DetailProductComponent implements OnInit {
  sanpham?:Sanpham;
  Ma:number=0;
  quantity:number=1;
  currentImageIndex:number=0;
  //hien thi thong bao
  showMessage = false;
  message = '';

  constructor(
  private productService :ProductService,
  private cartService:CartService,
  private router: Router,
  private  activateRoute:ActivatedRoute,
  private cartServicet: CartServiceT,
  private loveService:LoveService,
  ){

  }
  ngOnInit(): void {
    
    const idParam=this.activateRoute.snapshot.paramMap.get('id');
    
    if (idParam!=null)
    this.Ma=+idParam;
    if (!isNaN(this.Ma)){
              //tra ve mot san pham
            this.productService.getDetailProduct(this.Ma).subscribe({
          next: (response: any) => {
            
          
            if(response.sanpham_hinhs && response.sanpham_hinhs.length>0)
            {
             
            response.sanpham_hinhs.forEach((Sanpham_hinh:Sanphamhinh)=>{
              //set dia chi truc tiep
             Sanpham_hinh.diachihinh=`${environment.apiBaseUrl}/sanphams/images/${Sanpham_hinh.diachihinh}`;
           
            });
          }   
        
             response.Diachihinh=`${environment.apiBaseUrl}/sanphams/images/${response.hinhthunho}`;
          // alert(response.Diachihinh);
            
            
            this.sanpham=response;
                       
            this.showImage(0);
           
          },
          complete: () => {
            
          },
          error: (error: any) => {          
           console.error('Khong lay duoc danh sach hinh',error);
          }
        })   
    
      }
    }
    showImage(index:number):void{
      if(this.sanpham && this.sanpham.sanpham_hinhs
        && this.sanpham.sanpham_hinhs.length>0)
        if(index<0)
        index=0;
        else if(index>=this.sanpham.sanpham_hinhs.length)
        index=this.sanpham.sanpham_hinhs.length-1;
        this.currentImageIndex=index;
    }
    thumbnailClick(index:number){
      this.currentImageIndex=index;
    }
    nextImage():void{
      this.showImage(this.currentImageIndex+1);
    }
    previousImage():void{
      this.showImage(this.currentImageIndex-1);
    }
    increaseQuantity():void{
      this.quantity++;
    }
    decreaseQuantity():void{
      if(this.quantity>1)
      this.quantity--;
    }
  addToCart(): void {
    if (this.sanpham) {
      this.cartService.addToCart(this.Ma, this.quantity);

      //hien thi mess
       this.showMessage = true;
       this.message = "Đã thêm "+this.quantity+" sản phẩm "+this.sanpham.ten+" vào giỏ hàng thành công";
      setTimeout(() => {
        this.hideMessage();
      }, 2000);
    }
  }
  hideMessage() {
    this.showMessage = false;
  }
  addToLove(): void {

    this.loveService.addToLove(this.Ma,1);
    //hien thi mess
    this.showMessage = true;
    this.message = "Đã thêm sản phẩm "+this.sanpham?.ten+  " vào sản phẩm yêu thích thành công ";
    setTimeout(() => {
      this.hideMessage();
    }, 2000);
  }

    buyNow():void{
     
   
        this.cartServicet.addToCart(this.Ma, this.quantity);
        
        this.router.navigate(['ordert']);
        
     
   
    }

  }



