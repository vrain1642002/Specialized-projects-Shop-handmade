package com.project.shophandmade.repositories;


import com.project.shophandmade.models.Nguoidung;
import com.project.shophandmade.models.Sanpham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SanphamRepository extends JpaRepository<Sanpham, Long> {

  boolean existsSanphamByTen(String Ten);



  Page<Sanpham> findAll(Pageable pageable);//phân trang danh sach

  @Query(value = "SELECT * FROM sanpham " +
          "WHERE (:Ma IS NULL OR :Ma = 0 OR Ma_Danhmucsanpham = :Ma) " +
          "AND (:keyword IS NULL OR :keyword = '' OR LOWER(Ten) LIKE LOWER(CONCAT('%', :keyword, '%')))",
          countQuery = "SELECT COUNT(*) FROM sanpham " +
                  "WHERE (:Ma IS NULL OR :Ma = 0 OR Ma_Danhmucsanpham = :Ma) " +
                  "AND (:keyword IS NULL OR :keyword = '' OR LOWER(Ten) LIKE LOWER(CONCAT('%', :keyword, '%')))",
          nativeQuery = true)

  Page<Sanpham> searchProducts(@Param("Ma") Long Ma,
                               @Param("keyword") String keyword,
                               Pageable pageable);

  @Query("SELECT p FROM Sanpham p WHERE p.Ma IN :productIds")
  List<Sanpham> findProductsByIds(@Param("productIds") List<Long> productIds);
}
