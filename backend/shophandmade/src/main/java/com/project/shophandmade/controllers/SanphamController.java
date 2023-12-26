package com.project.shophandmade.controllers;

import com.github.javafaker.Faker;
import com.project.shophandmade.components.LocalizationUtils;
import com.project.shophandmade.dtos.SanphamDTO;
import com.project.shophandmade.dtos.Sanpham_hinhDTO;
import com.project.shophandmade.models.Sanpham;
import com.project.shophandmade.models.Sanpham_hinh;
import com.project.shophandmade.repositories.SanphamRepository;
import com.project.shophandmade.repositories.Sanpham_hinhRepository;
import com.project.shophandmade.responses.SanphamResponse;
import com.project.shophandmade.responses.SanphamsResponse;
import com.project.shophandmade.services.SanphamService;
import com.project.shophandmade.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/sanphams")
@RequiredArgsConstructor
public class SanphamController {
    private  final SanphamService sanphamService;


    private final LocalizationUtils localizationUtils;
    private final SanphamRepository sanphamRepository;
    private final Sanpham_hinhRepository sanpham_hinhRepository;
    @PostMapping(value = "" )
    public ResponseEntity<?> taoSanpham(
            @Valid @RequestBody SanphamDTO sanphamDTO,
            @ModelAttribute("files") List<MultipartFile> files,

            BindingResult result
    ){
         try {
             if(result.hasErrors()) {
                 List<String> errorMessages = result.getFieldErrors()
                         .stream()
                         .map(FieldError::getDefaultMessage)
                         .toList();
                 return ResponseEntity.badRequest().body(errorMessages);
             }

             Sanpham sanphammoi=sanphamService.taoSanpham(sanphamDTO);
             return ResponseEntity.ok(sanphammoi);
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
    }
    @PostMapping(value = "uploads/{Ma}",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadAnhs(
            @PathVariable("Ma") Long Ma,
            @ModelAttribute("files") List<MultipartFile> files
    )

    {
        try {
            Sanpham sanpham = sanphamService.laySanpham(Ma);
            files = files == null ? new ArrayList<MultipartFile>() : files;
            if(files.size()>Sanpham_hinh.soluongMax)
            {
                return ResponseEntity.badRequest().body(localizationUtils
                        .getLocalizedMessage(MessageKeys.UPLOAD_IMAGES_MAX_5));
            }
            // list cac hinh san pham
            List<Sanpham_hinh> sanpham_hinhs = new ArrayList<>();
            for (MultipartFile file : files) {
                if(file.getSize() == 0) {
                    continue;
                }
                // Kiểm tra kích thước file và định dạng
                if(file.getSize() > 10 * 1024 * 1024) { // Kích thước > 10MB
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body(localizationUtils
                                    .getLocalizedMessage(MessageKeys.UPLOAD_IMAGES_FILE_LARGE));
                }
                String contentType = file.getContentType();
                if(contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body(localizationUtils.getLocalizedMessage(MessageKeys.UPLOAD_IMAGES_FILE_MUST_BE_IMAGE));
                }
                // Lưu file và cập nhật dua cgu gubg trong DTO
                String filename = luuFile(file); //

                Sanpham_hinh sanpham_hinh=sanphamService.taoSanpham_hinh(
                        sanpham.getMa(),
                        Sanpham_hinhDTO.builder()
                                .Diachihinh(filename)
                                .build()
                );
                sanpham_hinhs.add(sanpham_hinh);

            }
            return ResponseEntity.ok().body(sanpham_hinhs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/images/{imageName}")
    public ResponseEntity<?> xemHinh(@PathVariable String imageName) {
        try {
            //lay ra duong dan truc tiep
            java.nio.file.Path imagePath = Paths.get("uploads/"+imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    private String luuFile(MultipartFile file) throws IOException {
         //lay ten file buoc phai doi tra ve ten goc
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        // Thêm UUID vào trước tên file để đảm bảo tên file là duy nhất
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        // Đường dẫn đến thư mục mà bạn muốn lưu file
        java.nio.file.Path uploadDir = Paths.get("uploads");
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
    @GetMapping("")
    public ResponseEntity<SanphamsResponse> laySanphams(
            @RequestParam(defaultValue = "")     String keyword,
            @RequestParam(defaultValue = "0")          Long Ma,
            @RequestParam(defaultValue = "0")     int page,
            @RequestParam(defaultValue = "0")    int limit
    ) {
        // Tạo Pageable từ thông tin trang và giới hạn
        //sap xep boi tao ,giam dan theo ngay tao
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("Ngaytao").descending());
        Page<SanphamResponse> sanphamPage = sanphamService.laySanphams(keyword,Ma,pageRequest);

        // Lấy tổng số trang
        int tongtrang = sanphamPage.getTotalPages();
        List<SanphamResponse> sanphams = sanphamPage.getContent();
        return ResponseEntity.ok(SanphamsResponse
                .builder()
                .sanphams(sanphams)
                .tongtrangs(tongtrang)
                 .build());

    }
    //http://localhost:8088/api/v1/sanpham/6
    @GetMapping("/{Ma}")
    public ResponseEntity<?> laySanpham(
            @PathVariable("Ma") Long Ma
    ) {
        try {
            Sanpham sanpham = sanphamService.laySanpham(Ma);
            SanphamResponse sanphamResponse=SanphamResponse.tuSanpham(sanpham);
            sanphamResponse.setSanpham_hinhs(sanpham_hinhRepository.findSanpham_hinhBySanpham(sanpham));
            return ResponseEntity.ok(sanphamResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{Ma}")
    public ResponseEntity<String> xoaSanpham(@PathVariable long Ma) {
        try {

            sanphamService.xoaSanpham(Ma);
            return ResponseEntity.ok(String.format("San pham co ma = %d da xoa thanh cong", Ma));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping("/giaDulieu")
    private ResponseEntity<String> giaDulieu() {
        Faker faker = new Faker();
        for (int i = 0; i < 70; i++) {
            String tenSanpham = faker.commerce().productName();
            if(sanphamService.tontaivoiTen(tenSanpham)) {
                continue;
            }

            SanphamDTO sanphamDTO=SanphamDTO.builder()
                    .Ten(tenSanpham)
                    .Gia((float)faker.number().numberBetween(10, 90_000_000))
                    .Mota(faker.lorem().sentence())
                    .Hinhthunho("")
                    //min<=x<max
                    .Madanhmucsanpham((long)faker.number().numberBetween(3, 4))
                    .build();
            try {
                sanphamService.taoSanpham(sanphamDTO);

            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.ok("Tao du lieu gia thanh cong");
    }


    @PutMapping("/{Ma}")
    public ResponseEntity<?> capnhatSanpham(
            @PathVariable long Ma,
            @RequestBody SanphamDTO sanphamDTO) {
        try {

            Sanpham sanphamcapnhat = sanphamService.capnhatSanpham(Ma, sanphamDTO);
            return ResponseEntity.ok(sanphamcapnhat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/by-ids")
    public ResponseEntity<?> getProductsByIds(@RequestParam("ids") String ids) {
        //ex: 1,4,7.7
        try {
            // Tách chuỗi ids thành một mảng các số nguyên
            List<Long> productIds = Arrays.stream(ids.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            List<Sanpham> sanphams=sanphamService.findProductsByIds(productIds);

            return ResponseEntity.ok(sanphams);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
