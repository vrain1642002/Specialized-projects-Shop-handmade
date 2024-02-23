package com.project.shophandmade.services;

import com.project.shophandmade.dtos.SanphamDTO;
import com.project.shophandmade.dtos.Sanpham_hinhDTO;
import com.project.shophandmade.responses.SanphamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.project.shophandmade.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ISanphamService {
     Sanpham taoSanpham(SanphamDTO sanphamDTO) throws Exception;
    Sanpham laySanpham(long Ma) throws Exception;
    Page<SanphamResponse> laySanphams(String keyword,Long Ma, PageRequest pageRequest);
    Sanpham capnhatSanpham(long Ma, SanphamDTO sanphamDTO ) throws Exception;
    void xoaSanpham(long Ma);
    boolean tontaivoiTen(String Ten);
    Sanpham_hinh taoSanpham_hinh(
            Long Ma,
            Sanpham_hinhDTO sanpham_hinhDTO) throws Exception;

    List<Sanpham> findProductsByIds(@Param("productIds") List<Long> productIds);
    String storeFile(MultipartFile file) throws IOException;
    void deleteFile(String filename) throws IOException;

}
