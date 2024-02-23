package com.project.shophandmade.repositories;

import com.project.shophandmade.models.Nguoidung;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

//JPA là một đặc tính Java được sử dụng để truy cập, quản lý và lưu trữ dữ liệu giữa đối tượng Java và database quan hệ.
public interface NguoidungRepository extends JpaRepository<Nguoidung, Long> {

    boolean existsBySdt(String SDT);
    //Doi tuong Optional kiem tra do tuong co empty khong
  Optional<Nguoidung> findBySdt(String SDT);





}

