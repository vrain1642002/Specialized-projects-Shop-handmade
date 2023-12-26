package com.project.shophandmade.controllers;

import com.project.shophandmade.dtos.ChitietdonhangDTO;
import com.project.shophandmade.exceptions.KhongtimthaydulieuException;
import com.project.shophandmade.models.Chitietdonhang;
import com.project.shophandmade.models.Donhang;
import com.project.shophandmade.services.ChitietdonhangService;
import com.project.shophandmade.services.DonhangService;
import com.project.shophandmade.services.IChitietdonhangService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/chitietdonhangs")
@RequiredArgsConstructor
public class ChitietdonhangController {
    private final IChitietdonhangService chitietdonhangService;

    @PostMapping("")
    public ResponseEntity<?> taoChitietdonhang(
            @Valid @RequestBody ChitietdonhangDTO chitietdonhangDTO) {
        try {

           Chitietdonhang chitietdonhang=chitietdonhangService.taochitietdonhang(chitietdonhangDTO);
            return  ResponseEntity.ok(chitietdonhang);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    @GetMapping("/{Ma}")
//    public ResponseEntity<?> layChitietdonhang(
//            @Valid @PathVariable("Ma") Long Ma) {
//        return ResponseEntity.ok("Chi tiet don hang voi ma = "+Ma);
//    }
    //lấy ra danh sách các order_details của 1 order nào đó
    @GetMapping("/Donhang/{Ma_Donhang}")
    public ResponseEntity<?> layChitietdonhangs(@Valid @PathVariable("Ma_Donhang") Long Ma_Donhang) {
        try {

            List<Chitietdonhang> chitietdonhangs=chitietdonhangService.chitietdonhangs(Ma_Donhang);
            return  ResponseEntity.ok(chitietdonhangs);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{Ma}")
    public ResponseEntity<String> xoaChitietdohang(@Valid @PathVariable("Ma") Long Ma) {
    try {

       chitietdonhangService.xoaChitietdonhang(Ma);
        return ResponseEntity.ok(String.format("Chi tiet don hang co ma = %d da xoa thanh cong",Ma));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
}
