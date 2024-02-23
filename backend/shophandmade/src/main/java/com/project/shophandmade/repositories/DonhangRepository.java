package com.project.shophandmade.repositories;


import com.project.shophandmade.models.Donhang;
import com.project.shophandmade.models.Nguoidung;
import com.project.shophandmade.models.Sanpham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DonhangRepository extends JpaRepository<Donhang, Long> {
    //Tìm các đơn hàng của 1 nguoidung nào đó
    //co che truyen van thong qua ten method quy dinh boi tien to va sau do la ten cua thuoctinh

    List<Donhang> findDonhangByNguoidung(Nguoidung nguoidung);

    @Query("SELECT o FROM Donhang o WHERE o.Trangthai = true AND (:keyword IS NULL OR :keyword = '' OR " +
            "o.Hoten_Nguoinhan LIKE %:keyword% " +
            "OR o.Sdt_Nguoinhan LIKE %:keyword% " +
            "OR o.Diachigiaohang LIKE %:keyword% " +
            "OR o.Ghichu LIKE %:keyword%)")
    Page<Donhang> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
