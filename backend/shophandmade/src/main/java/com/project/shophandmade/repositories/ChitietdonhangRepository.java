package com.project.shophandmade.repositories;


import com.project.shophandmade.models.Chitietdonhang;
import com.project.shophandmade.models.Donhang;
import com.project.shophandmade.models.Nguoidung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChitietdonhangRepository extends JpaRepository<Chitietdonhang,Long> {

    List<Chitietdonhang> findChitietdonhangByDonhang(Donhang donhang);
    void deleteChitietdonhangByDonhang(Donhang donhang);

}
