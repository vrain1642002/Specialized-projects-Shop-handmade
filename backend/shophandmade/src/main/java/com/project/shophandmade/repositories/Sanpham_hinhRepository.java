package com.project.shophandmade.repositories;


import com.project.shophandmade.models.Sanpham;
import com.project.shophandmade.models.Sanpham_hinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Sanpham_hinhRepository extends JpaRepository<Sanpham_hinh, Long> {
    List<Sanpham_hinh> findSanpham_hinhBySanpham(Sanpham sanpham);
}
