package com.project.shophandmade.controllers;



import com.project.shophandmade.models.Sanpham_hinh;
import com.project.shophandmade.services.IProductImageService;
import com.project.shophandmade.services.SanphamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/product_images")
//@Validated
//Dependency Injection
@RequiredArgsConstructor
public class ProductImageController {
    private final IProductImageService productImageService;
    private final SanphamService productService;
    @DeleteMapping("/{id}")
     public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        try {
           Sanpham_hinh productImage = productImageService.deleteProductImage(id);
            if(productImage != null){
                productService.deleteFile(productImage.getDiachihinh());
            }
            return ResponseEntity.ok(productImage);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
