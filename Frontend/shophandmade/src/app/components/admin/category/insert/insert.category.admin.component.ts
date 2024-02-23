import { Component } from '@angular/core';
import { InsertCategoryDTO } from 'src/app/dtos/category/insert.category.dto';
import { Danhmucsanpham } from 'src/app/models/Danhmucsanpham';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/categoryservice';
import { ProductService } from 'src/app/services/product.service';
import { OnInit } from '@angular/core';
@Component({
  selector: 'app-insert.category.admin',
  templateUrl: './insert.category.admin.component.html',
  styleUrls: ['./insert.category.admin.component.scss']
})
export class InsertCategoryAdminComponent implements OnInit {
  insertCategoryDTO: InsertCategoryDTO = {
    ten: '',    
  };
  categories: Danhmucsanpham[] = []; // Dữ liệu động từ categoryService
  constructor(    
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService,    
    private productService: ProductService,    
  ) {
    
  } 
  ngOnInit() {
    
  }   

  insertCategory() {  
    if (this.insertCategoryDTO.ten=="")
    {
      alert("Bạn chưa nhập tên danh mục sản phẩm");
      return;
    }

    this.categoryService.insertCategory(this.insertCategoryDTO).subscribe({
      next: (response) => {
        
        this.router.navigate(['/admin/categories']);        
        
      },
      error: (error) => {
        
        // Handle error while inserting the category
        alert(error.error.message)
        console.error('Error inserting category:', error);
     
      }
    });    
  }
}
