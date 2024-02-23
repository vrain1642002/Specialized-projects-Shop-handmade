package com.project.shophandmade.services;


import com.project.shophandmade.dtos.DanhmucsanphamDTO;
import com.project.shophandmade.models.Danhmucsanpham;
import com.project.shophandmade.models.*;

import java.util.List;

public interface IDanhmucsanphamService {
    Danhmucsanpham taoDanhmucsanpham(DanhmucsanphamDTO danhmucsanphamDTO) throws Exception;
    Danhmucsanpham layDanhmucsanpham(long Ma);
    List<Danhmucsanpham> layDanhmucsanphams();
    Danhmucsanpham capnhatDanhmucsanpham(long Ma, DanhmucsanphamDTO danhmucsanphamDTO);
    void xoaDanhmucsanpham(long id) throws Exception;
}
