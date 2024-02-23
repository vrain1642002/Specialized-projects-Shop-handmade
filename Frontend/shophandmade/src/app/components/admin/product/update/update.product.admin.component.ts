import { Component, OnInit } from '@angular/core';
import { Sanpham } from 'src/app/models/Sanpham';
import { UpdateProductDTO } from 'src/app/dtos/product/update.product.dto';
import { ProductService } from 'src/app/services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/categoryservice';
import { Danhmucsanpham } from 'src/app/models/Danhmucsanpham';
import { Sanphamhinh } from 'src/app/models/Sanphamhinh';
import { environment } from 'src/app/environments/environment';
import { Location } from '@angular/common';

@Component({
  selector: 'app-detail.product.admin',
  templateUrl: './update.product.admin.component.html',
  styleUrls: ['./update.product.admin.component.scss']
})

export class UpdateProductAdminComponent implements OnInit {
  productId: number;
  product: Sanpham;
  updatedProduct: Sanpham;
  categories: Danhmucsanpham[] = []; // Dữ liệu động từ categoryService
  currentImageIndex: number = 0;
  images: File[] = [];
  urlproduct:String="";

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService,    
    private location: Location,
  ) {
    this.productId = 0;
    this.product = {} as Sanpham;
    this.updatedProduct = {} as Sanpham;  
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.productId = Number(params.get('id'));
      this.getProductDetails();
    });

    this.getCategories(1, 100);
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
  getProductDetails(): void {
    debugger
    this.productService.getDetailProduct(this.productId).subscribe({
      next: (product:Sanpham): void => {
        this.product = product;
   

        this.updatedProduct = { ...product };  
        this.urlproduct=`${environment.apiBaseUrl}/sanphams/images/${product.hinhthunho}`;              
        this.updatedProduct.sanpham_hinhs.forEach((product_image:Sanphamhinh) => {
          product_image.diachihinh = `${environment.apiBaseUrl}/sanphams/images/${product_image.diachihinh}`;
        });
       
        
       
      },
      complete: () => {
        
      },
      error: (error: any) => {
        
      }
    });     
  }
  updateProduct() {
    // Implement your update logic here
    const updateProductDTO: UpdateProductDTO = {
      ten: this.updatedProduct.ten,
      gia: this.updatedProduct.gia,
      mota: this.updatedProduct.mota,
      hinhthunho:this.updatedProduct.hinhthunho,
      Ma_Danhmucsanpham: this.updatedProduct.madanhmucsanpham
      
    };
    this.productService.updateProduct(this.product.ma, updateProductDTO).subscribe({
      next: (response: any) => {  
        debugger        
      },
      complete: () => {
        debugger;
        this.router.navigate(['/admin/products']);        
      },
      error: (error: any) => {
        debugger;
        console.error('Error fetching products:', error);
      }
    });  
  }
  showImage(index: number): void {
    debugger
    if (this.product && this.product.sanpham_hinhs && 
        this.product.sanpham_hinhs.length > 0) {
      // Đảm bảo index nằm trong khoảng hợp lệ        
      if (index < 0) {
        index = 0;
      } else if (index >= this.product.sanpham_hinhs.length) {
        index = this.product.sanpham_hinhs.length - 1;
      }        
      // Gán index hiện tại và cập nhật ảnh hiển thị
      this.currentImageIndex = index;
    }
  }
  thumbnailClick(index: number,productImage: Sanphamhinh) {
    debugger
    this.currentImageIndex = index; // Cập nhật currentImageIndex
    const imageurl=productImage.diachihinh;
    const parts = imageurl.split('/');
   
    const imageNameWithExtension = parts[parts.length - 1];
    
    this.urlproduct=imageurl;
    this.updatedProduct.hinhthunho=imageNameWithExtension;
    // Gọi khi một thumbnail được bấm
  
  
  }  
  nextImage(): void {
    debugger
    this.showImage(this.currentImageIndex + 1);
  }

  previousImage(): void {
    debugger
    this.showImage(this.currentImageIndex - 1);
  }  
  onFileChange(event: any) {
    // Retrieve selected files from input element
    const files = event.target.files;
    // Limit the number of selected files to 4
    if (files.length > 4) {
      alert('Chỉ được chọn tối đa 4 hình');
      return;
    }
    // Store the selected files in the newProduct object
    this.images = files;
   
    this.productService.uploadImages(this.productId, this.images).subscribe({
      next: (imageResponse) => {
        debugger
        
        // Handle the uploaded images response if needed              
        console.log('up hình thành công', imageResponse);
        this.images = [];       
        // Reload product details to reflect the new images
        this.getProductDetails(); 
      },
      error: (error) => {
        // Handle the error while uploading images
        alert(error.error)
        console.error('Error uploading images:', error);
      }
    })
  }
  deleteImage(productImage: Sanphamhinh) {
    if (confirm('Bạn có chắc muốn xóa hình này')) {
      // Call the removeImage() method to remove the image   
      this.productService.deleteProductImage(productImage.ma).subscribe({
        next:(productImage:Sanphamhinh) => {
       
          history.go(0);        
        },        
        error: (error) => {
          // Handle the error while uploading images
          alert(error.error)
          console.error('Error deleting images:', error);
        }
      });
    }   
  }
}
