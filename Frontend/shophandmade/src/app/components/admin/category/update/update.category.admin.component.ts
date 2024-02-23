import { Component, OnInit } from '@angular/core';
import { UpdateCategoryDTO } from 'src/app/dtos/category/update.category.dto';
import { ActivatedRoute, Router } from '@angular/router';

import { CategoryService } from 'src/app/services/categoryservice';
import { Danhmucsanpham } from 'src/app/models/Danhmucsanpham';
import { environment } from 'src/app/environments/environment';

@Component({
  selector: 'app-detail.category.admin',
  templateUrl: './update.category.admin.component.html',
  styleUrls: ['./update.category.admin.component.scss']
})

export class UpdateCategoryAdminComponent implements OnInit {
  categoryId: number;
  updatedCategory: Danhmucsanpham;
  
  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router,
  
  ) {
    this.categoryId = 0;    
    this.updatedCategory = {} as Danhmucsanpham;  
  }

  ngOnInit(): void {    
    this.route.paramMap.subscribe(params => {
      debugger
      this.categoryId = Number(params.get('id'));
    
     
      // this.getCategoryDetails();
    });
    
  }
  
  getCategoryDetails(): void {
    this.categoryService.getDetailCategory(this.categoryId).subscribe({
      next: (category: Danhmucsanpham) => {        
        this.updatedCategory = { ...category };  
        alert(this.updatedCategory.ten);                      
      },
      complete: () => {
        
      },
      error: (error: any) => {
        
      }
    });     
  }
  updateCategory() {
    // Implement your update logic here
    const updateCategoryDTO: UpdateCategoryDTO = {
      name: this.updatedCategory.ten,      
    };
    this.categoryService.updateCategory(this.categoryId, updateCategoryDTO).subscribe({
      next: (response: any) => {  
        debugger        
      },
      complete: () => {
        debugger;
        this.router.navigate(['/admin/categories']);        
      },
      error: (error: any) => {
        debugger;
        console.error('Error fetching categorys:', error);
      }
    });  
  }  
}
