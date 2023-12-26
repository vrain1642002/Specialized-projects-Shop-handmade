import { Component, OnInit } from '@angular/core';
import { Sanpham } from 'src/app/models/Sanpham';
import { ProductService } from '../../services/product.service';
import { environment } from 'src/app/environments/environment';
import { Danhmucsanpham } from 'src/app/models/Danhmucsanpham';
import { CategoryService } from 'src/app/services/categoryservice';

import { Router, RouterLink } from '@angular/router';
import { CartService } from 'src/app/services/cartservice';
import { LoveService } from 'src/app/services/loveservice';
import { CartServiceT } from 'src/app/services/tcartservice';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
//ham onInit duoc goi khi component duoc load le
export class ProductComponent implements OnInit {
  sanphams: Sanpham[] = [];
  danhmucsanphams: Danhmucsanpham[] = [];
  currentPage: number = 0;
  itemsPerPage: number = 8;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";
  selecteMaDM: number = 0;
  selecteMa: number = 0;
  //hien thi
  showMessage = false;
  message = '';


  constructor(private productService: ProductService,
    private categoryService: CategoryService,
    private router: Router,
    private cartService: CartService,
    private cartServicet: CartServiceT,
    private loveService:LoveService,
  ) { }
  ngOnInit(): void {
    this.getProducts(this.keyword, this.selecteMaDM, this.currentPage, this.itemsPerPage);
    this.getCategories(0, 100);
  }
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
  getCategories(page: number, limit: number) {
    this.categoryService.getCategories(page, limit).subscribe({
      next: (response: any) => {


        this.danhmucsanphams = response;


      },
      complete: () => {

      },
      error: (error: any) => {
        console.error('Khong lay duoc danh sach san pham', error);
      }
    })

  }
  onPageChange(page: number) {
    this.currentPage = page;
    this.getProducts(this.keyword, this.selecteMaDM, this.currentPage, this.itemsPerPage);
  }
  search() {


    this.getProducts(this.keyword, this.selecteMaDM, this.currentPage, this.itemsPerPage);
    this.onPageChange(0);
  }

  generateVisiblePageArray(currentPage: number, totalPages: number): number[] {
    const maxVisiblePage = 5;
    const halfVisiblePage = Math.floor(maxVisiblePage / 2);
    let startPage = Math.max(currentPage - halfVisiblePage, 0);
    let endPage = Math.min(startPage + maxVisiblePage - 1, totalPages - 1);
    if (endPage - startPage + 1 < maxVisiblePage)
      startPage = Math.max(endPage - maxVisiblePage + 1, 0);
    return new Array(endPage - startPage + 1).fill(0).map((_, index) => startPage + index);
    //hien thi cac trang
  }
  onProductClick(Ma: number) {
    this.router.navigate(['products', Ma]);
  }
  addToCart(Ma: number,Ten:string): void {

    this.cartService.addToCart(Ma, 1);

    //hien thi mess
    this.showMessage = true;
    this.message = "Đã thêm sản phẩm "+Ten+  " vào giỏ hàng thành công";
    setTimeout(() => {
      this.hideMessage();
    }, 2000);
  }
  addToCart2(Ma: number,Ten:string): void {
   
   this.cartServicet.addToCart(Ma,1);
   
   this.router.navigate(['ordert']);
   
  }
  hideMessage() {
    this.showMessage = false;
  }
  addToLove(Ma: number,Ten:string): void {

    this.loveService.addToLove(Ma,1);
    //hien thi mess
    this.showMessage = true;
    this.message = "Đã thêm sản phẩm "+Ten+  " vào sản phẩm yêu thích thành công ";
    setTimeout(() => {
      this.hideMessage();
    }, 4000);
  }
 
}


