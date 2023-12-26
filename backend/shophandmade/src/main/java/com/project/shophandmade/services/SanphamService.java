package com.project.shophandmade.services;


import com.project.shophandmade.dtos.SanphamDTO;
import com.project.shophandmade.dtos.Sanpham_hinhDTO;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.exceptions.GiatrikhonghopleException;
import com.project.shophandmade.models.Danhmucsanpham;
import com.project.shophandmade.models.Sanpham;
import com.project.shophandmade.models.Sanpham_hinh;
import com.project.shophandmade.repositories.DanhmucsanphamRepository;
import com.project.shophandmade.repositories.SanphamRepository;
import com.project.shophandmade.repositories.Sanpham_hinhRepository;
import com.project.shophandmade.responses.SanphamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SanphamService implements ISanphamService {
    private final SanphamRepository sanphamRepository;
    private final DanhmucsanphamRepository danhmucsanphamRepository;
    private final Sanpham_hinhRepository sanpham_hinhRepository;


    @Override
    public Sanpham taoSanpham(SanphamDTO sanphamDTO) throws Exception {
        Danhmucsanpham Danhmucsanpham =danhmucsanphamRepository
                .findById(sanphamDTO.getMadanhmucsanpham())
                .orElseThrow(() ->
                        new KhongtimthaydulieuException(
                                "Khong tim thay  danh muc san pham co ma = "+sanphamDTO.getMadanhmucsanpham()));

        Sanpham sanphammoi = Sanpham.builder()
                .ten(sanphamDTO.getTen())
                .gia(sanphamDTO.getGia())
                .hinhthunho(sanphamDTO.getHinhthunho())
                .Mota(sanphamDTO.getMota())
                .danhmucsanpham(Danhmucsanpham)
                .build();

        return sanphamRepository.save(sanphammoi);
    }

    @Override
    public Sanpham laySanpham(long Ma) throws Exception {
        return sanphamRepository.findById(Ma).
                orElseThrow(()-> new KhongtimthaydulieuException(
                        "Khong tim thay san pham voi ma =" +Ma));

    }

    //chuyen doi doi tuong san pham sang san pham reponse,reponse hien thi xu ly o client
    @Override
    public Page<SanphamResponse> laySanphams(String keyword,Long Ma, PageRequest pageRequest) {
//
      return sanphamRepository.searchProducts(Ma,keyword,pageRequest).map(sanpham ->SanphamResponse.tuSanpham(sanpham));
//         return  sanphamRepository.findAll(pageRequest).map(sanpham ->SanphamResponse.tuSanpham(sanpham));
//

    }

    @Override
    public Sanpham capnhatSanpham(long Ma, SanphamDTO sanphamDTO) throws Exception {

        Sanpham sanpham=laySanpham(Ma);
        Danhmucsanpham Danhmucsanpham =danhmucsanphamRepository
                .findById(sanphamDTO.getMadanhmucsanpham())
                .orElseThrow(() ->
                        new KhongtimthaydulieuException(
                                "Khong tim thay danh muc san pham co ma = "+sanphamDTO.getMadanhmucsanpham()));
        sanpham.setTen(sanphamDTO.getTen());
        sanpham.setDanhmucsanpham(Danhmucsanpham);
        sanpham.setGia(sanphamDTO.getGia());
        sanpham.setHinhthunho(sanphamDTO.getHinhthunho());
        sanpham.setMota(sanphamDTO.getMota());
        return  sanphamRepository.save(sanpham);
    }

    @Override
    public void xoaSanpham(long Ma) {
        Optional<Sanpham> optionalSanpham = sanphamRepository.findById(Ma);
       //neu ton tai gia tri
        if (optionalSanpham.isPresent()) {
            sanphamRepository.delete(optionalSanpham.get());
        }
    }

    @Override
    public boolean tontaivoiTen(String Ten) {
        return sanphamRepository.existsSanphamByTen(Ten);
    }

    @Override
    public Sanpham_hinh taoSanpham_hinh(Long Ma, Sanpham_hinhDTO sanpham_hinhDTO) throws Exception {

            Sanpham Sanpham = sanphamRepository
                    .findById(Ma)
                    .orElseThrow(() ->
                            new KhongtimthaydulieuException(
                                    "khong tim thay san pham voi ma "+sanpham_hinhDTO.getMa_Sanpham()));
           Sanpham_hinh newSanpham_hinh = Sanpham_hinh.builder()
                    .sanpham(Sanpham)
                    .Diachihinh(sanpham_hinhDTO.getDiachihinh())
                   .build();


            //Ko cho insert quá 4 ảnh cho 1 sản phẩm
            int size = sanpham_hinhRepository.findSanpham_hinhBySanpham(Sanpham).size();
            if(size >= Sanpham_hinh.soluongMax) {
                throw new GiatrikhonghopleException("So luong hinh <="+ Sanpham_hinh.soluongMax);
            }
             Sanpham.setHinhthunho(newSanpham_hinh.getDiachihinh());
              sanphamRepository.save(Sanpham);
             return  sanpham_hinhRepository.save(newSanpham_hinh);
    }

    @Override
    public List<Sanpham> findProductsByIds(List<Long> productIds) {
        return sanphamRepository.findProductsByIds(productIds);
    }

}
