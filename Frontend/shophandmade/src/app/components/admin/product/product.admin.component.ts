import { Component, OnInit } from '@angular/core';
import { Sanpham } from 'src/app/models/Sanpham';
import { Danhmucsanpham } from 'src/app/models/Danhmucsanpham';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { CategoryService } from 'src/app/services/categoryservice';
import { TokenService } from 'src/app/services/token.service';
import { environment } from 'src/app/environments/environment';
import { UpdateProductDTO } from 'src/app/dtos/product/update.product.dto';
import { Location } from '@angular/common';

@Component({
  selector: 'app-product-admin',
  templateUrl: './product.admin.component.html',
  styleUrls: [
    './product.admin.component.scss',        
  ]
})
export class ProductAdminComponent implements OnInit {
    sanphams: Sanpham[] = [];    
    selectedCategoryId: number  = 0; // Giá trị category được chọn
    selecteMaDM: number = 0;
    currentPage: number = 0;
    itemsPerPage: number = 7;
    pages: number[] = [];
    totalPages:number = 0;
    visiblePages: number[] = [];
    keyword:string = "";
    constructor(
      private productService: ProductService,      
      private router: Router,     
      private location: Location 
    ) {

    }
    ngOnInit() {
      this.currentPage = Number(localStorage.getItem('currentProductAdminPage')) || 0; 
      this.getProducts(this.keyword, 
        this.selectedCategoryId, 
        this.currentPage, this.itemsPerPage);  
        this.onPageChange(0);    
    }    
    searchProducts() {
      this.currentPage = 0;
      this.itemsPerPage = 7;
      //Mediocre Iron Wallet
      debugger
      this.getProducts(this.keyword.trim(), this.selectedCategoryId, this.currentPage, this.itemsPerPage);
    }
    // getProducts(keyword: string, selectedCategoryId: number, page: number, limit: number) {
    //   debugger
    //   this.productService.getProducts(keyword, selectedCategoryId, page, limit).subscribe({
    //     next: (response: any) => {
    //       debugger
    //       response.products.forEach((product: Sanpham) => {                      
    //         if (product) {
    //           product.diachihinh = `${environment.apiBaseUrl}/products/images/${product.hinhthunho}`;
    //         }          
    //       });
    //       this.products = response.products;
    //       this.totalPages = response.totalPages;
    //       this.visiblePages = this.generateVisiblePageArray(this.currentPage, this.totalPages);
    //     },
    //     complete: () => {
    //       debugger;
    //     },
    //     error: (error: any) => {
    //       debugger;
    //       console.error('Error fetching products:', error);
    //     }
    //   });    
    // }
    getProducts(keyword: string, selecteMaDM: number, page: number, limit: number) {
      this.productService.getProducts(keyword, selecteMaDM, page, limit).subscribe({
        next: (response: any) => {
  
          response.sanphams.forEach((Sanpham: Sanpham) => {
            //set dia chi truc tiep
            Sanpham.diachihinh = `${environment.apiBaseUrl}/sanphams/images/${Sanpham.hinhthunho}`;
  
          });
          this.sanphams = response.sanphams;
          //response la ket qua tra ve cua back end
  
          this.totalPages = response.tongtrangs;
  
          this.visiblePages = this.generateVisiblePageArray(this.currentPage, this.totalPages);
        },
        complete: () => {
  
        },
        error: (error: any) => {
          console.error('Khong lay duoc danh sach san pham', error);
        }
      })
  
    }
    onPageChange(page: number) {
      debugger;
      this.currentPage = page < 0 ? 0 : page;
      localStorage.setItem('currentProductAdminPage', String(this.currentPage));     
      this.getProducts(this.keyword, this.selectedCategoryId, this.currentPage, this.itemsPerPage);
      
    }
  
    // generateVisiblePageArray(currentPage: number, totalPages: number): number[] {
    //   const maxVisiblePages = 5;
    //   const halfVisiblePages = Math.floor(maxVisiblePages / 2);
    
    //   let startPage = Math.max(currentPage - halfVisiblePages, 1);
    //   let endPage = Math.min(startPage + maxVisiblePages - 1, totalPages);
    
    //   if (endPage - startPage + 1 < maxVisiblePages) {
    //     startPage = Math.max(endPage - maxVisiblePages + 1, 1);
    //   }
    
    //   return new Array(endPage - startPage + 1).fill(0)
    //     .map((_, index) => startPage + index);
    // }
    generateVisiblePageArray(currentPage: number, totalPages: number): number[] {
      const maxVisiblePage = 5;
      const halfVisiblePage = Math.floor(maxVisiblePage / 2);
      let startPage = Math.max(currentPage - halfVisiblePage, 0);
      let endPage = Math.min(startPage + maxVisiblePage - 1, totalPages - 1);
      if (endPage - startPage + 1 < maxVisiblePage)
        startPage = Math.max(endPage - maxVisiblePage + 1, 0);
      return new Array(endPage - startPage + 1).fill(0).map((_, index) => startPage + index);
    }
    
    // Hàm xử lý sự kiện khi thêm mới sản phẩm
    insertProduct() {
      debugger
      // Điều hướng đến trang detail-product với productId là tham số
      this.router.navigate(['/admin/products/insert']);
    } 

    // Hàm xử lý sự kiện khi sản phẩm được bấm vào
    updateProduct(productId: number) {
      debugger
      // Điều hướng đến trang detail-product với productId là tham số
      this.router.navigate(['/admin/products/update', productId]);
    }  
    deleteProduct(product: Sanpham) {      
      const confirmation = window
      .confirm('Bạn có chắc muốn xóa sản phẩm này');
      if (confirmation) {
        debugger
        this.productService.deleteProduct(product.ma).subscribe({
          next: (response: string) => {
            debugger 
            alert('Xóa thành công')
            this.onPageChange(0);     
          },
          complete: () => {
            debugger;          
          },
          error: (error) => {
            debugger;
           
            console.error('Error fetching products:', error);
          }
        });  
      }      
    }      
}