import { Component } from '@angular/core';
import { InsertProductDTO } from 'src/app/dtos/product/insert.product.dto';
import { Sanpham } from 'src/app/models/Sanpham';
import { Danhmucsanpham } from 'src/app/models/Danhmucsanpham';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { CategoryService } from 'src/app/services/categoryservice';
import { OnInit } from '@angular/core';
@Component({
  selector: 'app-insert.product.admin',
  templateUrl: './insert.product.admin.component.html',
  styleUrls: ['./insert.product.admin.component.scss']
})
export class InsertProductAdminComponent implements OnInit {
  insertProductDTO: InsertProductDTO = {
    ten: '',
    gia: 0,
    mota: '',
    Ma_Danhmucsanpham: 0,
    hinhs: []
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
    this.getCategories(1, 100)
  } 
  getCategories(page: number, limit: number) {
    this.categoryService.getCategories(page, limit).subscribe({
      next: (categories: Danhmucsanpham[]) => {
        debugger
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
  onFileChange(event: any) {
    // Retrieve selected files from input element
    const files = event.target.files;
    // Limit the number of selected files to 5
    if (files.length > 4) {
      alert('Chỉ được chọn 4 hình mời bạn chọn lại');
      return;
    }
    // Store the selected files in the newProduct object
    this.insertProductDTO.hinhs = files;
    
  }

  insertProduct() {
    if (this.insertProductDTO.ten=="")
    {
      alert("Bạn chưa nhập tên sản phẩm");
      return;
    }
    if (this.insertProductDTO.gia==0 )
    {
      alert("Bạn chưa nhập giá");
      return;
    }
    if (this.insertProductDTO.Ma_Danhmucsanpham==0 )
    {
      alert("Bạn chưa chọn danh mục");
      return;
    }
    if (this.insertProductDTO.hinhs.length==0 )
    {
      alert("Bạn chưa chọn hình");
      return;
    }
   
      
      
    this.productService.insertProduct(this.insertProductDTO).subscribe({
      next: (response) => {
        debugger
        if (this.insertProductDTO.hinhs.length > 0) {
          const productId = response.ma; // Assuming the response contains the newly created product's ID
          this.productService.uploadImages(productId, this.insertProductDTO.hinhs).subscribe({
            next: (imageResponse) => {
              debugger
              // Handle the uploaded images response if needed              
              console.log('Images uploaded successfully:', imageResponse);
              // Navigate back to the previous page
              this.router.navigate(['../'], { relativeTo: this.route });
            },
            error: (error) => {
              // Handle the error while uploading images
              alert(error.error)
              console.error('Error uploading images:', error);
            }
          })          
        }
      },
      error: (error) => {
        debugger
        // Handle error while inserting the product
        alert(error.error)
        console.error('Error inserting product:', error);
      }
    });    
  }
}
