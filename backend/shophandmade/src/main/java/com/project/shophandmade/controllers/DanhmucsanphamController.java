package com.project.shophandmade.controllers;

import com.project.shophandmade.dtos.*;
import com.project.shophandmade.dtos.DanhmucsanphamDTO;
import com.project.shophandmade.models.Danhmucsanpham;
import com.project.shophandmade.repositories.DanhmucsanphamRepository;
import com.project.shophandmade.responses.DangnhapResponse;
import com.project.shophandmade.responses.DanhmucsanphamResponse;
import com.project.shophandmade.responses.UpdateCategoryResponse;
import com.project.shophandmade.services.DanhmucsanphamService;
import com.project.shophandmade.services.IDanhmucsanphamService;
import com.project.shophandmade.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

//ket hop giua controller danh dau class xu ly request va tra ket qua thong qua cac api xu ly nhu get,post,put,delete
//truyen cac json object qua thanh request object
@RestController
@RequestMapping("${api.prefix}/danhmucsanphams")
//@Validated
//Dependency Injection goi toi services
@RequiredArgsConstructor
public class DanhmucsanphamController {
    private final IDanhmucsanphamService danhmucsanphamService;

      @PostMapping("")
    //Tham so truyen vao la 1 object=>Data Transfre object= Request Object
    public ResponseEntity<DanhmucsanphamResponse> taoDanhmucsanpham(
            @Valid @RequestBody DanhmucsanphamDTO danhmucsanphamDTO,
            BindingResult result) {
//
          DanhmucsanphamResponse danhmucsanphamResponse=new DanhmucsanphamResponse();
          try {

              Danhmucsanpham danhmucsanpham = danhmucsanphamService.taoDanhmucsanpham(danhmucsanphamDTO);
              danhmucsanphamResponse.setDanhmucsanpham(danhmucsanpham);
              // Trả về token trong response
              return ResponseEntity.ok(danhmucsanphamResponse);

                            } catch (Exception e) {
              danhmucsanphamResponse.setMessage(e.getMessage());
              return ResponseEntity.badRequest().body(danhmucsanphamResponse);


          }
    }
    @GetMapping("")  //http://localhost:8088/api/v1/Danhmucsanpham
    public ResponseEntity<List<Danhmucsanpham>>  LayDanhmucsanphams(
            @RequestParam ("page") int page,
            @RequestParam ("limit") int limit

    ){
        List<Danhmucsanpham> danhmucsanphams = danhmucsanphamService.layDanhmucsanphams();
        return ResponseEntity.ok(danhmucsanphams);
    }

    @PutMapping("/{Ma}")
    public ResponseEntity<UpdateCategoryResponse> suaDanhmucsanpham(@PathVariable Long Ma, @Valid @RequestBody DanhmucsanphamDTO danhmucsanphamDTO){
        UpdateCategoryResponse updateCategoryResponse = new UpdateCategoryResponse();
        danhmucsanphamService.capnhatDanhmucsanpham(Ma,danhmucsanphamDTO);

        updateCategoryResponse.setMessage("Cập nhật thành công");
        return ResponseEntity.ok(updateCategoryResponse);
    }


    @DeleteMapping("/{Ma}")
    public ResponseEntity<?> xoaDanhmucsanpham(@PathVariable Long Ma){
          try {
              danhmucsanphamService.xoaDanhmucsanpham(Ma);
              return ResponseEntity.ok(String.format(""));
          }catch (Exception e) {
              return ResponseEntity.badRequest().body(e.getMessage());
          }

    }



}
