package com.project.shophandmade.services;


import com.project.shophandmade.dtos.DanhmucsanphamDTO;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.models.Danhmucsanpham;
import com.project.shophandmade.models.Sanpham;
import com.project.shophandmade.repositories.DanhmucsanphamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//danh dau la class xu ly tang luan li,logic
@Service
//kiem tra thuoc tinh nao la final thi khoi dung tung ung de dependency inejection
@RequiredArgsConstructor
public class DanhmucsanphamService implements IDanhmucsanphamService {
    private final DanhmucsanphamRepository danhmucsanphamRepository;


    @Override
    public Danhmucsanpham taoDanhmucsanpham(DanhmucsanphamDTO danhmucsanphamDTO) throws Exception{
        String Ten= danhmucsanphamDTO.getTen();
        if(danhmucsanphamRepository.existsByTen(Ten)) {

            throw  new KhongtimthaydulieuException("Đã tồn tại tên danh mục");


        }
        //builder la ham khoi tao tung thanh phan tung thuoc tinh 1
        Danhmucsanpham newDanhmucsanpham = Danhmucsanpham
                .builder()
                .ten(danhmucsanphamDTO.getTen())
                .build();
        return danhmucsanphamRepository.save(newDanhmucsanpham);
    }

    @Override
    public Danhmucsanpham layDanhmucsanpham(long Ma) {
        return danhmucsanphamRepository.findById(Ma)
                .orElseThrow(() -> new RuntimeException("Danh muc san pham khong tim thay"));

    }

    @Override
    public List<Danhmucsanpham> layDanhmucsanphams() {

        return danhmucsanphamRepository.findAll();
    }

    @Override
    public Danhmucsanpham capnhatDanhmucsanpham(long Ma, DanhmucsanphamDTO danhmucsanphamDTO) {

            Danhmucsanpham danhmucsanpham =layDanhmucsanpham(Ma);
            danhmucsanpham.setTen(danhmucsanphamDTO.getTen());
            danhmucsanphamRepository.save(danhmucsanpham);
            return danhmucsanpham;

    }

    @Override
    public void xoaDanhmucsanpham(long Ma) throws Exception {
       int sl=danhmucsanphamRepository.countProductsByMaDanhMuc(Ma);
        if(sl>0) {

            throw  new KhongtimthaydulieuException("Không thể xóa danh mục sản phẩm vì tồn tại sản phẩm");


        }
        danhmucsanphamRepository.deleteById(Ma);
    }

}
