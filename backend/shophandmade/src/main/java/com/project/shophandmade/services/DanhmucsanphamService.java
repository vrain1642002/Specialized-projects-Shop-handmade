package com.project.shophandmade.services;


import com.project.shophandmade.dtos.DanhmucsanphamDTO;
import com.project.shophandmade.models.Danhmucsanpham;
import com.project.shophandmade.repositories.DanhmucsanphamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//danh dau la class xu ly tang luan li,logic
@Service
//kiem tra thuoc tinh nao la final thi khoi dung tung ung de dependency inejection
@RequiredArgsConstructor
public class DanhmucsanphamService implements IDanhmucsanphamService {
    private final DanhmucsanphamRepository danhmucsanphamRepository;


    @Override
    public Danhmucsanpham taoDanhmucsanpham(DanhmucsanphamDTO danhmucsanphamDTO) {
        //builder la ham khoi tao tung thanh phan tung thuoc tinh 1
        Danhmucsanpham newDanhmucsanpham = Danhmucsanpham
                .builder()
                .Ten(danhmucsanphamDTO.getTen())
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
    public void xoaDanhmucsanpham(long Ma) {
            danhmucsanphamRepository.deleteById(Ma);
    }

}
