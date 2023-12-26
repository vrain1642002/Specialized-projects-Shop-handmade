package com.project.shophandmade.services;


import com.project.shophandmade.dtos.ChitietdonhangDTO;
import com.project.shophandmade.dtos.DonhangDTO;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.models.Chitietdonhang;
import com.project.shophandmade.models.Donhang;
import com.project.shophandmade.models.Sanpham;
import com.project.shophandmade.repositories.ChitietdonhangRepository;
import com.project.shophandmade.repositories.DonhangRepository;
import com.project.shophandmade.repositories.SanphamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChitietdonhangService implements IChitietdonhangService{


    private final DonhangRepository donhangRepository;
    private final ChitietdonhangRepository chitietdonhangRepository;
    private final SanphamRepository sanphamRepository;
    @Override
    public Chitietdonhang taochitietdonhang(ChitietdonhangDTO chitietdonhangDTO) throws Exception {
        //tìm xem don hang có tồn tại ko
        Donhang donhang=donhangRepository.findById(chitietdonhangDTO.getMaDonhang())
                .orElseThrow(() -> new KhongtimthaydulieuException("Khong tim thay don hang voi ma : "+chitietdonhangDTO.getMaDonhang()));

        // Tìm san pham
        Sanpham sanpham=sanphamRepository.findById(chitietdonhangDTO.getMaSanpham())
                .orElseThrow(() -> new KhongtimthaydulieuException("Khong tim thay san pham voi ma : "+chitietdonhangDTO.getMaSanpham()));
       Chitietdonhang chitietdonhang=Chitietdonhang.builder()
                .donhang(donhang)
                .sanpham(sanpham)
               .Soluong(chitietdonhangDTO.getSoluong())
               .Gia(chitietdonhangDTO.getGia())
               .Thanhtien(chitietdonhangDTO.getThanhtien())
               .build();
       return  chitietdonhangRepository.save(chitietdonhang);

    }

    @Override
    public List<Chitietdonhang> chitietdonhangs(Long Ma_Donhang) throws Exception {
        Donhang donhang=donhangRepository.findById(Ma_Donhang).orElseThrow(()->
                new KhongtimthaydulieuException("Khong tim thay don hang co ma don hang "+Ma_Donhang));
        return  chitietdonhangRepository.findChitietdonhangByDonhang(donhang);

    }

    public Optional<Chitietdonhang> chitietdonhang(Long Ma) throws Exception {

        Chitietdonhang chitietdonhang=chitietdonhangRepository.findById(Ma).orElseThrow(()->
                new KhongtimthaydulieuException("Khong tim thay chi tiet don hang co ma don hang "+Ma));
        return  chitietdonhangRepository.findById(Ma);

    }
    @Override
    public void xoaChitietdonhang(Long Ma) throws KhongtimthaydulieuException {


        Optional<Chitietdonhang> optionalChitietdonhang=chitietdonhangRepository.findById(Ma);
        //neu ton tai gia tri
        if (optionalChitietdonhang.isPresent())
            chitietdonhangRepository.delete(optionalChitietdonhang.get());

    }
}
