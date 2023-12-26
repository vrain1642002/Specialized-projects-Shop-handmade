package com.project.shophandmade.services;


import com.project.shophandmade.components.JwtTokenUtil;
import com.project.shophandmade.dtos.CapnhatnguoidungDTO;
import com.project.shophandmade.dtos.NguoidungDTO;
import com.project.shophandmade.exceptions.ExpiredTokenException;
import com.project.shophandmade.exceptions.GiatrikhonghopleException;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.exceptions.NganquyenException;
import com.project.shophandmade.models.Nguoidung;
import com.project.shophandmade.models.Vaitro;
import com.project.shophandmade.repositories.NguoidungRepository;
import com.project.shophandmade.repositories.VaitroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//inject cac doi tuong final
@RequiredArgsConstructor
@Service
public class NguoidungService implements INguoidungService {
    private final NguoidungRepository nguoidungRepository;
    private final  VaitroRepository vaitroRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtTokenUtil jwtTokenUtil;
    private  final AuthenticationManager authenticationManager;

    @Override
    public Nguoidung taoNguoidung(NguoidungDTO nguoidungDTO) throws Exception {
        String SDT = nguoidungDTO.getSDT();

        // Kiểm tra xem số điện thoại đã tồn tại hay chưa
        if(nguoidungRepository.existsBySdt(SDT)) {
            throw new DataIntegrityViolationException("SDT đã tồn tại");
        }

        Vaitro vaitro =vaitroRepository.findById(nguoidungDTO.getMaVaitro())
                .orElseThrow(() -> new KhongtimthaydulieuException("Khong tim thay vai tro"));
        if(vaitro.getTen().toUpperCase().equals(Vaitro.ADMIN))
            throw  new NganquyenException("Khong the dang ki tai khoan ADMIN");

            if(!nguoidungDTO.getMatkhau().equals(nguoidungDTO.getNhaplaimatkhau()))
            {
                throw  new KhongtimthaydulieuException("Mat khau va nhap lai mat khau khongdung");

            }
        //convert nguoidung dto => user
        Nguoidung nguoidungmoi=Nguoidung.builder()
                .Hoten(nguoidungDTO.getHoten())
                 .sdt(nguoidungDTO.getSDT())

                 .Diachi(nguoidungDTO.getDiachi())
                .Ngaysinh(nguoidungDTO.getNgaysinh()).build();
        nguoidungmoi.setTrangthai(true);


        nguoidungmoi.setVaitro(vaitro);
        String matkhau= nguoidungDTO.getMatkhau();
        String matkhauMahoa = passwordEncoder.encode(matkhau);
        nguoidungmoi.setMatkhau(matkhauMahoa);
        //}
        return nguoidungRepository.save(nguoidungmoi);

    }

    @Override
    public String dangnhap(String SDT, String Matkhau) throws Exception
    {
        Optional<Nguoidung> optionalNguoidung=nguoidungRepository.findBySdt(SDT);
        if (optionalNguoidung.isEmpty())
            throw  new KhongtimthaydulieuException("Khong tim thay tai khoan");
        Nguoidung nguoidung=optionalNguoidung.get();
        //kiem tra mat khau
        if(!passwordEncoder.matches(Matkhau,nguoidung.getPassword()))
            throw  new BadCredentialsException("Sai so dien thoai hoac mat khau");
        //cap quyen bang spring secutity
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(SDT,Matkhau);
          authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.taoToken(nguoidung);
    }
    @Override
    public Nguoidung getUserDetailsFromToken(String token) throws Exception {
        if(jwtTokenUtil.kiemtrahanToken(token)) {
            throw new ExpiredTokenException("Token is expired");
        }
        String sdt = jwtTokenUtil.laySDT(token);
          Optional<Nguoidung> nguoidung=nguoidungRepository.findBySdt(sdt);

        if (nguoidung.isPresent()) {
            return nguoidung.get();
        } else {
            throw new Exception("Không tìm thấy người dùng");
        }
    }
    @Override
    public Nguoidung capnhatNguoidung(Long userId, CapnhatnguoidungDTO capnhatnguoidungDTOO) throws Exception {
        // Find the existing user by userId

        Nguoidung nguoidung=nguoidungRepository.findById(userId)
                .orElseThrow(() -> new KhongtimthaydulieuException("khong tim thay nguoi dung"));
        // Check if the phone number is being changed and if it already exists for another user



        if(capnhatnguoidungDTOO.getHoten()!=null)
            nguoidung.setHoten(capnhatnguoidungDTOO.getHoten());

        if(capnhatnguoidungDTOO.getDiachi()!=null)
            nguoidung.setDiachi(capnhatnguoidungDTOO.getDiachi());

        if(capnhatnguoidungDTOO.getNgaysinh()!=null)
            nguoidung.setNgaysinh(capnhatnguoidungDTOO.getNgaysinh());



        if(capnhatnguoidungDTOO.getMatkhau()!=null && capnhatnguoidungDTOO.getNhaplaimatkhau()!=null) {
            if(capnhatnguoidungDTOO.getMatkhau().equals(capnhatnguoidungDTOO.getNhaplaimatkhau())==false)
            {
                throw  new GiatrikhonghopleException("Mat khau va nhap lai mat khau khongdung");

            }
            String newPassword = capnhatnguoidungDTOO.getMatkhau();
                  String encodedPassword = passwordEncoder.encode(newPassword);
            nguoidung.setMatkhau(encodedPassword);
        }


        return nguoidungRepository.save(nguoidung);
    }



}
