package com.project.shophandmade.services;


import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.models.Sanpham_hinh;
import com.project.shophandmade.repositories.ProductImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImageService implements IProductImageService{
    private final ProductImageRepository productImageRepository;
    @Override
    @Transactional
    public Sanpham_hinh deleteProductImage(Long id) throws Exception {
        Optional<Sanpham_hinh> productImage = productImageRepository.findById(id);
        if(productImage.isEmpty()) {
            throw new KhongtimthaydulieuException(
                    String.format("Cannot find product image with id: %ld", id)
            );
        }
        productImageRepository.deleteById(id);
        return productImage.get();
    }
}
