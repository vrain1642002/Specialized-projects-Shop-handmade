import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { CategoryService } from 'src/app/services/categoryservice';

import { Danhmucsanpham } from 'src/app/models/Danhmucsanpham';

@Component({
  selector: 'app-category-admin',
  templateUrl: './category.admin.component.html',
  styleUrls: [
    './category.admin.component.scss',        
  ]
})
export class CategoryAdminComponent implements OnInit {
  categories: Danhmucsanpham[] = []; // Dữ liệu động từ categoryService
  constructor(    
    private categoryService: CategoryService,    
    private router: Router,    
    ) {}
    
    ngOnInit() {      
      this.getCategories(0, 100);
    }
    getCategories(page: number, limit: number) {
      this.categoryService.getCategories(page, limit).subscribe({
        next: (categories: Danhmucsanpham[]) => {
          debugger;
          this.categories = categories;
        },
        complete: () => {
          debugger;
        },
        error: (error: any) => {
          console.error('Error fetching categories:', error);
        }
      });
    }
    insertCategory() {
      debugger
      // Điều hướng đến trang detail-category với categoryId là tham số
      this.router.navigate(['/admin/categories/insert']);
    } 

    // Hàm xử lý sự kiện khi sản phẩm được bấm vào
    updateCategory(categoryId: number) {
      debugger      
      this.router.navigate(['/admin/categories/update', categoryId]);
    }  
    
    deleteCategory(category: Danhmucsanpham) {      
      const confirmation = window
      .confirm('Bạn có chắc xóa danh mục này?');
      if (confirmation) {
  
        this.categoryService.deleteCategory(category.ma).subscribe({
          next: (response:string) => {
           debugger
            alert('Xóa thành công')
            this.getCategories(0, 100);         
          },
          complete: () => {
                   
          },
          error: (error) => {
           
            alert(error.error)
            console.error('Error fetching categories:', error);
          }
        });  
      }      
    }
}