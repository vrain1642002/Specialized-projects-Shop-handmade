package com.project.shophandmade.controllers;

import com.project.shophandmade.dtos.DonhangDTO;
import com.project.shophandmade.models.Donhang;
import com.project.shophandmade.responses.DonhangResponse;
import com.project.shophandmade.responses.OrderListResponse;
import com.project.shophandmade.services.DonhangService;
import com.project.shophandmade.services.IDonhangService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/donhangs")
@RequiredArgsConstructor
public class DonhangController {
    private final IDonhangService donhangService;

    @PostMapping("")
    public ResponseEntity<?> taoDonhang(
            @Valid @RequestBody DonhangDTO donhangDTO,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);

            }
            Donhang donhang = donhangService.taoDonhang(donhangDTO);
            return ResponseEntity.ok(donhang);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/nguoidung/{Ma_Nguoidung}") // Thêm biến đường dẫn ma nguoi dung

    public ResponseEntity<?> layDonhangs(@Valid @PathVariable("Ma_Nguoidung") Long Ma_Nguoidung) {
        try {

            List<Donhang> donhangs = donhangService.layDonhangs(Ma_Nguoidung);
            return ResponseEntity.ok(donhangs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{Ma}") //

    public ResponseEntity<?> layDonhang(@Valid @PathVariable("Ma") Long Ma) {
        try {

            Donhang donhang = donhangService.layDonhang(Ma);
            DonhangResponse donhangResponse = DonhangResponse.fromDonhang(donhang);
            return ResponseEntity.ok(donhangResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{Ma}")
    //PUT http://localhost:8088/api/v1/Donhangs/2
    //công việc của admin
    public ResponseEntity<?> capnhatDonhang(
            @Valid @PathVariable long Ma,
            @Valid @RequestBody DonhangDTO donhangDTO) {
        try {
            Donhang donhang = donhangService.capnhatDonhang(Ma, donhangDTO);
            return ResponseEntity.ok(donhang);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{Ma}")
    public ResponseEntity<String> xoaDonhang(@Valid @PathVariable Long Ma) {
        //xóa mềm
        donhangService.xoaDonhang(Ma);
        return ResponseEntity.ok("Xoa don hang thanh cong");
    }

    @GetMapping("/get-orders-by-keyword")
    public ResponseEntity<OrderListResponse> getOrdersByKeyword(
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        // Tạo Pageable từ thông tin trang và giới hạn
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                //Sort.by("createdAt").descending()
                Sort.by("Ma").ascending()
        );
        Page<DonhangResponse> orderPage = donhangService
                .getOrdersByKeyword(keyword, pageRequest)
                .map(DonhangResponse::fromDonhang);
        // Lấy tổng số trang
        int totalPages = orderPage.getTotalPages();
        List<DonhangResponse> orderResponses = orderPage.getContent();
        return ResponseEntity.ok(OrderListResponse
                .builder()
                .orders(orderResponses)
                .totalPages(totalPages)
                .build());
    }

}
