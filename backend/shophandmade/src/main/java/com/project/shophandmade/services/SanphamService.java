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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SanphamService implements ISanphamService {
    private final SanphamRepository sanphamRepository;
    private final DanhmucsanphamRepository danhmucsanphamRepository;
    private final Sanpham_hinhRepository sanpham_hinhRepository;
        private static String UPLOADS_FOLDER = "uploads";

    @Override
    public Sanpham taoSanpham(SanphamDTO sanphamDTO) throws Exception {
        Danhmucsanpham Danhmucsanpham =danhmucsanphamRepository
                .findById(sanphamDTO.getMadanhmucsanpham())
                .orElseThrow(() ->
                        new KhongtimthaydulieuException(
                                "Không tìm thấy danh mục sản phẩm với mã "+sanphamDTO.getMadanhmucsanpham()));

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
                        "Không tìm thấy sản phẩm với mã " +Ma));

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
                                "KKhông tìm thấy sản phẩm với mã "+sanphamDTO.getMadanhmucsanpham()));
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
                                    "Không tìm thấy danh mục sản phẩm với mã  "+sanpham_hinhDTO.getMa_Sanpham()));
           Sanpham_hinh newSanpham_hinh = Sanpham_hinh.builder()
                    .sanpham(Sanpham)
                    .Diachihinh(sanpham_hinhDTO.getDiachihinh())
                   .build();


            //Ko cho insert quá 4 ảnh cho 1 sản phẩm
            int size = sanpham_hinhRepository.findSanpham_hinhBySanpham(Sanpham).size();
            if(size >= Sanpham_hinh.soluongMax) {
                throw new GiatrikhonghopleException("Số lượng hình <="+ Sanpham_hinh.soluongMax);
            }
             Sanpham.setHinhthunho(newSanpham_hinh.getDiachihinh());
              sanphamRepository.save(Sanpham);
             return  sanpham_hinhRepository.save(newSanpham_hinh);
    }

    @Override
    public List<Sanpham> findProductsByIds(List<Long> productIds) {
        return sanphamRepository.findProductsByIds(productIds);
    }
    @Override
    public void deleteFile(String filename) throws IOException {
        // Đường dẫn đến thư mục chứa file
        java.nio.file.Path uploadDir = Paths.get(UPLOADS_FOLDER);
        // Đường dẫn đầy đủ đến file cần xóa
        java.nio.file.Path filePath = uploadDir.resolve(filename);

        // Kiểm tra xem file tồn tại hay không
        if (Files.exists(filePath)) {
            // Xóa file
            Files.delete(filePath);
        } else {
            throw new FileNotFoundException("File not found: " + filename);
        }
    }
    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
    @Override
    public String storeFile(MultipartFile file) throws IOException {
        if (!isImageFile(file) || file.getOriginalFilename() == null) {
            throw new IOException("Invalid image format");
        }
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        // Thêm UUID vào trước tên file để đảm bảo tên file là duy nhất
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        // Đường dẫn đến thư mục mà bạn muốn lưu file
        java.nio.file.Path uploadDir = Paths.get(UPLOADS_FOLDER);
        // Kiểm tra và tạo thư mục nếu nó không tồn tại
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        // Đường dẫn đầy đủ đến file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
        // Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }
}
