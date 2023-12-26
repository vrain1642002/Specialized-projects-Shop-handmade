package com.project.shophandmade.repositories;


import com.project.shophandmade.models.Donhang;
import com.project.shophandmade.models.Nguoidung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DonhangRepository extends JpaRepository<Donhang, Long> {
    //Tìm các đơn hàng của 1 nguoidung nào đó
    //co che truyen van thong qua ten method quy dinh boi tien to va sau do la ten cua thuoctinh

    List<Donhang> findDonhangByNguoidung(Nguoidung nguoidung);
}
