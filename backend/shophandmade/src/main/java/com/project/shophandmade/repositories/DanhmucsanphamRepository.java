package com.project.shophandmade.repositories;


import com.project.shophandmade.models.Danhmucsanpham;
import com.project.shophandmade.models.Nguoidung;
import com.project.shophandmade.models.Sanpham;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

//repository la noi chua cac ham de tao tac voi model roi tu model xuong CSDL
//Gan nhu java spring da ho tro nhu class JpaRepository ho tro cac pt co ban nhu get,getall,add,update,delete
public interface DanhmucsanphamRepository extends JpaRepository<Danhmucsanpham, Long> {
  boolean existsByTen(String Ten);

  @Query("SELECT COUNT(p) FROM Sanpham p WHERE p.danhmucsanpham.Ma = :Ma")
  int countProductsByMaDanhMuc(@Param("Ma") Long Ma);


//




}
