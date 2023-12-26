package com.project.shophandmade.controllers;

import com.project.shophandmade.dtos.*;
import com.project.shophandmade.dtos.DanhmucsanphamDTO;
import com.project.shophandmade.models.Danhmucsanpham;
import com.project.shophandmade.repositories.DanhmucsanphamRepository;
import com.project.shophandmade.services.DanhmucsanphamService;
import com.project.shophandmade.services.IDanhmucsanphamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<?> taoDanhmucsanpham(
            @Valid @RequestBody DanhmucsanphamDTO danhmucsanphamDTO,
            BindingResult result) {
        if(result.hasErrors()) {
            //lay danh sach loi chuyen dang string bang stream de bao loi
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        danhmucsanphamService.taoDanhmucsanpham(danhmucsanphamDTO);
        return ResponseEntity.ok("tao thanh cong"+danhmucsanphamDTO);
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
    public ResponseEntity<String> suaDanhmucsanpham(@PathVariable Long Ma, @Valid @RequestBody DanhmucsanphamDTO danhmucsanphamDTO){
        danhmucsanphamService.capnhatDanhmucsanpham(Ma,danhmucsanphamDTO);
        return ResponseEntity.ok("Cap nhat danh muc san pham voi ma ="+Ma +" thanh cong");
    }
    @DeleteMapping("/{Ma}")
    public ResponseEntity<String> xoaDanhmucsanpham(@PathVariable Long Ma){
        danhmucsanphamService.xoaDanhmucsanpham(Ma);
        return ResponseEntity.ok("Xoa danh muc san pham voi ma="+Ma+" thanh cong");
    }



}
