package com.project.shophandmade.services;

import com.project.shophandmade.dtos.CartItemDTO;
import com.project.shophandmade.dtos.DonhangDTO;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.models.*;
import com.project.shophandmade.repositories.ChitietdonhangRepository;
import com.project.shophandmade.repositories.DonhangRepository;
import com.project.shophandmade.repositories.NguoidungRepository;

import com.project.shophandmade.repositories.SanphamRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DonhangService implements IDonhangService{
    private final NguoidungRepository nguoidungRepository;
    private final DonhangRepository donhangRepository;
    private final ModelMapper modelMapper;
    private final SanphamRepository sanphamRepository;
    private final ChitietdonhangRepository chitietdonhangRepository;

    @Override
    public Donhang taoDonhang(DonhangDTO donhangDTO) throws KhongtimthaydulieuException {
        //tìm xem nguoi dung có tồn tại ko

        Nguoidung nguoidung=nguoidungRepository.findById(donhangDTO.getMaNguoidung())
                .orElseThrow(() ->new KhongtimthaydulieuException("Khong tim thay nguoi dung co ma " +donhangDTO.getMaNguoidung()));

//       //dung thu vien mapper de conver nguoidungdto sang nguoi dung
//        modelMapper.typeMap(DonhangDTO.class,Donhang.class)
//                .addMappings(mapper->mapper.skip(Donhang::setMa));
//
//        // Cập nhật các trường của đơn hàng từ orderDTO
//        Donhang donhang=new Donhang();
//        modelMapper.map(donhangDTO,donhang);
//        System.out.println(donhang.getSdt_Nguoinhan());
//        donhang.setNguoidung(nguoidung);
//        donhang.setNgaydat( LocalDateTime.now());
//        donhang.setTrangthaiDH(TrangthaiDH.Dangchuanbi);
//         donhang.setTrangthai(true);
//        donhangRepository.save(donhang);
        Donhang donhang=Donhang.builder()
                .Hoten_Nguoinhan(donhangDTO.getHotenNguoinhan())
                .sdt_Nguoinhan(donhangDTO.getSDTNguoinhan())
                 .Ghichu(donhangDTO.getGhichu())
                 .Tongtien(donhangDTO.getTongtien())
                .Phuongthucvanchuyen(donhangDTO.getPhuongthucvanchuyen())
                .Diachigiaohang(donhangDTO.getDiachigiaohang())
                .Phuongthucthanhtoan(donhangDTO.getPhuongthucthanhtoan())
                .build();
        donhang.setNguoidung(nguoidung);
        donhang.setNgaydat( LocalDate.now());
        donhang.setTrangthaiDH(TrangthaiDH.Dangchuanbi);
         donhang.setTrangthai(true);
         donhangRepository.save(donhang);

        List<Chitietdonhang> chitietdonhangs = new ArrayList<>();
        for (CartItemDTO cartItemDTO : donhangDTO.getCartItems()) {
            // Tạo một đối tượng chitiet don hang từ CartItemDTO


            Chitietdonhang chitietdonhang =new Chitietdonhang();
            chitietdonhang.setDonhang(donhang);

            // Lấy thông tin sản phẩm từ cartItemDTO
            Long productId = cartItemDTO.getProductId();
            int quantity = cartItemDTO.getQuantity();


            Sanpham sanpham=sanphamRepository.findById(productId)
                    .orElseThrow(() -> new KhongtimthaydulieuException("Product not found with id: " + productId));
            chitietdonhang.setSanpham(sanpham);
            chitietdonhang.setSoluong(quantity);
            chitietdonhang.setGia(sanpham.getGia());
            chitietdonhang.setThanhtien(chitietdonhang.getSoluong()*chitietdonhang.getGia());
            // Đặt thông tin cho OrderDetail


            // Thêm OrderDetail vào danh sách
            chitietdonhangs.add(chitietdonhang);
        }


        // Lưu danh sách OrderDetail vào cơ sở dữ liệu
        chitietdonhangRepository.saveAll(chitietdonhangs);
        donhangRepository.save(donhang);
        return  donhang;
    }

    @Override
    public Donhang layDonhang(long Ma) {

        return  donhangRepository.findById(Ma).orElse(null);
    }

    @Override
    public List<Donhang> layDonhangs(Long Ma_nguoidung) throws KhongtimthaydulieuException {
        Nguoidung nguoidung=nguoidungRepository.findById(Ma_nguoidung).orElseThrow(()->
                new KhongtimthaydulieuException("Khong tim thay nguoi dung co ma nguoi dung "+Ma_nguoidung));
        Optional<Nguoidung> nguoidung1=nguoidungRepository.findById(Ma_nguoidung);

         return  donhangRepository.findDonhangByNguoidung(nguoidung);

    }

    @Override
    public Donhang capnhatDonhang(long Ma, DonhangDTO donhangDTO) throws KhongtimthaydulieuException {

        Donhang donhang=donhangRepository.findById(Ma).orElseThrow(()->
                new KhongtimthaydulieuException("Khong tim thay don hang voi ma don hang "+Ma));
        Nguoidung nguoidung=nguoidungRepository.findById(donhangDTO.getMaNguoidung()).orElseThrow(()->
                new KhongtimthaydulieuException("Khong tim thay nguoi dung voi ma"+donhangDTO.getMaNguoidung()));
        modelMapper.typeMap(DonhangDTO.class,Donhang.class)
                .addMappings(mapper->mapper.skip(Donhang::setMa));
        modelMapper.map(donhangDTO,donhang);
        donhang.setNguoidung(nguoidung);
        return  donhangRepository.save(donhang);
        // Cập nhật các trường của đơn hàng từ orderDTO
    }

    @Override
    public void xoaDonhang(long Ma) {

        Donhang donhang=donhangRepository.findById(Ma).orElse(null);
        //xoa mem
        if (donhang!=null)
        {
            donhang.setTrangthai(false);
            donhangRepository.save(donhang);
        }

    }
}
