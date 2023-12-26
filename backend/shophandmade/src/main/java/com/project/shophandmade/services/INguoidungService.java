package com.project.shophandmade.services;


import com.project.shophandmade.dtos.CapnhatnguoidungDTO;
import com.project.shophandmade.dtos.NguoidungDTO;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.models.Nguoidung;

public interface INguoidungService {
    Nguoidung taoNguoidung(NguoidungDTO nguoidungDTO) throws Exception;
    String dangnhap(String SDT, String Matkhau) throws Exception;
    Nguoidung getUserDetailsFromToken(String token) throws Exception;
    Nguoidung capnhatNguoidung(Long userId, CapnhatnguoidungDTO capnhatnguoidungDTOO) throws Exception;
}
