package com.project.shophandmade.repositories;


import com.project.shophandmade.models.Sanpham_hinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<Sanpham_hinh, Long> {
}
