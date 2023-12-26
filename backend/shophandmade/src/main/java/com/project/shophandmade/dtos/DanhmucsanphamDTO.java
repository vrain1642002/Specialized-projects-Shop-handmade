package com.project.shophandmade.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

//anonation
@Data//to string
@Getter
@Setter
@Builder
//ham khoi dung
@AllArgsConstructor
@NoArgsConstructor

//Day la data tranfer object dung truyen doi tuong tu client
public class DanhmucsanphamDTO {
    //ID sinh o duoi co so du lieu nen k can
    @NotEmpty(message = "Khong duoc de trong ten danh muc")
    private String Ten;

}
