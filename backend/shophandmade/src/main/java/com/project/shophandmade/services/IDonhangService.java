package com.project.shophandmade.services;



import com.project.shophandmade.dtos.DanhmucsanphamDTO;
import com.project.shophandmade.dtos.DonhangDTO;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.models.Danhmucsanpham;
import com.project.shophandmade.models.Donhang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDonhangService {


    Donhang taoDonhang(DonhangDTO donhangDTO) throws KhongtimthaydulieuException;
    Donhang layDonhang(long Ma);
    List<Donhang> layDonhangs(Long Ma_nguoidung) throws KhongtimthaydulieuException;
    Donhang capnhatDonhang(long Ma,DonhangDTO donhangDTO ) throws KhongtimthaydulieuException;
    void xoaDonhang(long Ma);
    Page<Donhang> getOrdersByKeyword(String keyword, Pageable pageable);

}
