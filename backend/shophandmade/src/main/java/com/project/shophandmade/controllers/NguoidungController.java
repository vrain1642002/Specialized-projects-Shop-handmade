package com.project.shophandmade.controllers;

import com.project.shophandmade.components.LocalizationUtils;
import com.project.shophandmade.dtos.CapnhatnguoidungDTO;
import com.project.shophandmade.dtos.NguoidungDTO;
import com.project.shophandmade.dtos.NguoidungLogin;
import com.project.shophandmade.models.Nguoidung;
import com.project.shophandmade.responses.DangkiResponse;
import com.project.shophandmade.responses.DangnhapResponse;
import com.project.shophandmade.responses.NguidungResponse;
import com.project.shophandmade.services.INguoidungService;
import com.project.shophandmade.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/nguoidungs")
@RequiredArgsConstructor
public class NguoidungController {
    private final INguoidungService nguoidungService;
    private final LocalizationUtils localizationUtils;
    @PostMapping(value = "/dangki")
            //Kieu uploadd file mutipart

    public ResponseEntity<DangkiResponse> taoNguoidung(
            @Valid @RequestBody NguoidungDTO nguoidungDTO,
            BindingResult result
    ) {
        DangkiResponse dangkiResponse = new DangkiResponse();

        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            dangkiResponse.setMessage(errorMessages.toString());
            dangkiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PASSWORD_NOT_MATCH));
            return ResponseEntity.badRequest().body(dangkiResponse);
        }
        if(!nguoidungDTO.getMatkhau() .equals(nguoidungDTO.getNhaplaimatkhau())){
            return ResponseEntity.badRequest().body(dangkiResponse);
        }
        try{

            Nguoidung nguoidung=nguoidungService.taoNguoidung(nguoidungDTO);
            dangkiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.REGISTER_SUCCESSFULLY));
            dangkiResponse.setNguoidung(nguoidung);
            return ResponseEntity.ok(dangkiResponse);
        }  catch (Exception e) {
            dangkiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.WRONG_PHONE_PASSWORD));
            return ResponseEntity.badRequest().body(dangkiResponse);
        }
    }

    @PostMapping("/dangnhap")
    public ResponseEntity<DangnhapResponse> Dangnhap(@Valid @RequestBody NguoidungLogin nguoidungLogin){
        // Kiểm tra thông tin đăng nhập và sinh token
        try {

            String token =nguoidungService.dangnhap(nguoidungLogin.getSDT(),nguoidungLogin.getMatkhau());
            // Trả về token trong response
            return ResponseEntity.ok(DangnhapResponse.builder()
                    .message(localizationUtils.getLocalizedMessage(MessageKeys.LOGIN_SUCCESSFULLY))
                    .token(token)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    DangnhapResponse.builder()
                            .message(localizationUtils.getLocalizedMessage(MessageKeys.LOGIN_FAILED, e.getMessage()))
                            .build()
            );
        }
    }

    @PostMapping("/chitiets")
    public ResponseEntity<NguidungResponse> getUserDetails(@RequestHeader("Authorization") String token){
        // Kiểm tra thông tin đăng nhập và sinh token
       try
       {
           String extractedToken =token.substring(7); // Loại bỏ "Bearer " từ chuỗi token
           Nguoidung nguoidung=nguoidungService.getUserDetailsFromToken(extractedToken);

           return ResponseEntity.ok(NguidungResponse.fromNguoidung(nguoidung));
       }catch (Exception e) {
           return ResponseEntity.badRequest().build();
       }


    }
    @PutMapping("/chitiets/{userId}")


    public ResponseEntity<NguidungResponse> updateUserDetails(
            @PathVariable Long userId,
            @RequestBody CapnhatnguoidungDTO capnhatnguoidungDTO,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        try {
            String extractedToken = authorizationHeader.substring(7);
            Nguoidung nguoidung=nguoidungService.getUserDetailsFromToken(extractedToken);
            if(nguoidung.getMa()!=userId)
            {
                return ResponseEntity.status(HttpStatus.FOUND).build();

            }

            Nguoidung updatedUser=nguoidungService.capnhatNguoidung(userId,capnhatnguoidungDTO);
            return  ResponseEntity.ok(NguidungResponse.fromNguoidung(updatedUser) );


        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
