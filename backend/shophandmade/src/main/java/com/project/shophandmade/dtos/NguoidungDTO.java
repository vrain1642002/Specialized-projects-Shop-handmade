package com.project.shophandmade.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NguoidungDTO {
    @NotBlank
    @JsonProperty("Hoten")
    private String Hoten;

    @JsonProperty("SDT")
    @NotBlank(message = "SDT la can thiet ")
    private String SDT;



    @JsonProperty("Diachi")
    private String Diachi;

    @JsonProperty("Matkhau")
    @NotBlank(message = "Mat khau khong duoc de trong")
    private String Matkhau;

    @JsonProperty("Nhaplaimatkhau")
    @NotBlank(message = "Mat khau nhap lai khong duoc de trong")
    private String Nhaplaimatkhau;

    @JsonProperty("Ngaysinh")
    private Date Ngaysinh;


    @NotNull(message = "Ma vai tro la can thiet")
    @JsonProperty("Ma_Vaitro")
    private Long MaVaitro;
}
