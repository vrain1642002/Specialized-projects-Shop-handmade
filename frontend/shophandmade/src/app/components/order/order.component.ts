import { Component, EnvironmentInjector, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavigationEnd, Router } from '@angular/router';
import { Sanpham } from 'src/app/models/Sanpham';
import { CartService } from 'src/app/services/cartservice';
import { ProductService } from 'src/app/services/product.service';
import { environment } from 'src/app/environments/environment';
import { OrderDTO } from 'src/app/dtos/order/order.dto';
import { OrderService } from 'src/app/services/order.service';
import { Donhang } from 'src/app/models/Donhang';
import { TokenService } from 'src/app/services/token.service';
import { CartServiceT } from 'src/app/services/tcartservice';



@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  orderForm: FormGroup; // Đối tượng FormGroup để quản lý dữ liệu của form
  cartItems: { sanpham: Sanpham, quantity: number }[] = [];
  totalAmount: number = 0;
  cart: Map<number, number> = new Map();
  orderData: OrderDTO = {
    Ma_Nguoidung: 13, // Thay bằng user_id thích hợp
    Hoten_Nguoinhan: '', // Khởi tạo rỗng, sẽ được điền từ form
    SDT_Nguoinhan: '', // Khởi tạo rỗng, sẽ được điền từ form
    Diachigiaohang: '', // Khởi tạo rỗng, sẽ được điền từ form
     Ghichu: '', // Có thể thêm trường ghi chú nếu cần
    Tongtien: 0, // Sẽ được tính toán dựa trên giỏ hàng và mã giảm giá
    Phuongthucthanhtoan: 'cod', // Mặc định là thanh toán khi nhận hàng (COD)
    Phuongthucvanchuyen: 'express', // Mặc định là vận chuyển nhanh (Express)
   // Sẽ được điền từ form khi áp dụng mã giảm giá
    cart_items: []
    
  };
 
  constructor(
    
    private cartService: CartService,
    private cartServicet: CartServiceT,
    private productService: ProductService,
    private router: Router,
    private orderService: OrderService,
    private formBuilder: FormBuilder,
    private tokenService: TokenService,
  

  ) { 
    this.orderForm = this.formBuilder.group({
      fullname: ['', Validators.required], // fullname là FormControl bắt buộc      
      phone_number: ['', [Validators.required, Validators.minLength(10)]], //
      address: ['', [Validators.required, Validators.minLength(10)]], // 
      note: [''],
      shipping_method: ['express'],
      payment_method: ['cod']
    });

  }
  ngOnInit(): void {
      
    var currentUrl = window.location.href;
       //neu la trang dat ngay thi hien danh sach dat ngay
    if (currentUrl.includes('/ordert'))
    {
    this.cart=this.cartServicet.getCart();
  
    }
    //nguoc lai la hien danh sach tu gio hang
    else this.cart=this.cartService.getCart();

     if (this.cart.size==0)
      {
      alert("Bạn chưa chọn sản phẩm nào");
      this.router.navigate(['cart']);
      return;
      }
    //chuyen danh sach ID tu map gio hang
    const productIds = Array.from(this.cart.keys());
    //goi service de lay thong tin san pham du tren danh sach id

    this.productService.getProductsByIds(productIds).subscribe({
      next: (sanphams) => {
        this.cartItems = productIds.map((productId) => {
          
            const sanpham = sanphams.find((s) => s.ma === productId);
            if (sanpham) {

              sanpham.hinhthunho = `${environment.apiBaseUrl}/sanphams/images/${sanpham.hinhthunho}`;
            }
            return{
              sanpham:sanpham!,
              quantity:this.cart.get(productId)!
            };
          });

        },
          complete: () => {
            
           this.calculateTotal();
          },
          error: (error: any) => {
            console.error('Khong lay duoc danh sach san pham', error);
          }
        })
  }
  placeOrder() {
  
    if (this.tokenService.getToken()=="")
   
    {
      alert("Bạn phải đăng nhập để có thể đặt hàng");
      this.router.navigate(['login']);
      return;
    }
    if (this.orderForm.errors == null) {
   
      this.orderData.Hoten_Nguoinhan = this.orderForm.get('fullname')!.value;
      this.orderData.SDT_Nguoinhan = this.orderForm.get('phone_number')!.value;
      this.orderData.Diachigiaohang= this.orderForm.get('address')!.value;
      this.orderData.Ghichu = this.orderForm.get('note')!.value;
      this.orderData.Phuongthucvanchuyen = this.orderForm.get('shipping_method')!.value;
      this.orderData.Phuongthucthanhtoan = this.orderForm.get('payment_method')!.value;
     
      
      
      this.orderData.cart_items = this.cartItems.map(cartItem => ({
        product_id: cartItem.sanpham.ma,
        quantity: cartItem.quantity
      }));
      this.orderData.Tongtien =  this.totalAmount;
    
      // Dữ liệu hợp lệ, bạn có thể gửi đơn hàng đi
      this.orderService.placeOrder(this.orderData).subscribe({
        next: (response:Donhang) => {
         
          
          alert('Đặt hàng thành công với mã đơn hàng là '+response.ma+' hãy tra cứu đơn hàng');
          
          this.cartService.clearCart();
          this.cartService.clearCart();
          this.router.navigate(['/find']);
        },
        complete: () => {
          debugger;
          this.calculateTotal();
        },
        error: (error: any) => {
          debugger;
          alert(`Lỗi khi đặt hàng: ${error}`);
        },
      });
    } else {
      // Hiển thị thông báo lỗi hoặc xử lý khác
      alert('Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.');
    }        
  }
    
  

  
  calculateTotal():void{
    this.totalAmount=this.cartItems.reduce(
      (total,item)=> total + item.sanpham.gia * item.quantity,0
    )
  }
  clear():void{
    this.cartService.clearCart();
    window.location.reload();
  }
  onProductClick(Ma: number) {
    this.router.navigate(['products', Ma]);
  }
//   decreaseQuantity(index:number,id:number){
   
//     if(this.cartItems[index].quantity==1)   
//     {         
//     this.cart.delete(id);
 
//     }
//    else 
//      this.cartItems[index].quantity--;
//     this.cartService.saveCartToLocalStorage()
//     this.calculateTotal();
  
// }
// increaseQuantity(index:number){
//   this.cartItems[index].quantity++;
//   this.cartService.saveCartToLocalStorage()
//   this.calculateTotal();
// }
// onEnterKey(index:number,quantity:number,id:number){
//   if(quantity==0)  
//   {
//     this.cart.delete(id);
      
//     return;
//   }
//   else 
//   this.cartItems[index].quantity=quantity;
    
//   this.cartService.saveCartToLocalStorage()
//   this.calculateTotal();
// }
}
