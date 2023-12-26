package com.project.shophandmade.repositories;


import com.project.shophandmade.models.Danhmucsanpham;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

//repository la noi chua cac ham de tao tac voi model roi tu model xuong CSDL
//Gan nhu java spring da ho tro nhu class JpaRepository ho tro cac pt co ban nhu get,getall,add,update,delete
public interface DanhmucsanphamRepository extends JpaRepository<Danhmucsanpham, Long> {


//




}
