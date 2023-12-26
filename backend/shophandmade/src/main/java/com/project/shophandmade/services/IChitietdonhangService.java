package com.project.shophandmade.services;


import com.project.shophandmade.dtos.ChitietdonhangDTO;
import com.project.shophandmade.dtos.DonhangDTO;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.models.Chitietdonhang;

import java.util.List;
import java.util.Optional;

public interface IChitietdonhangService {


    Chitietdonhang taochitietdonhang(ChitietdonhangDTO chitietdonhangDTO) throws  Exception;
    Optional<Chitietdonhang> chitietdonhang(Long Ma) throws  Exception;
    List<Chitietdonhang> chitietdonhangs(Long Ma_Donhang) throws  Exception;
    void xoaChitietdonhang(Long Ma) throws KhongtimthaydulieuException;

}
