package com.project.shophandmade.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ChitietdonhangDTO {
    @JsonProperty("Ma_Donhang")
    @Min(value=1, message = "Ma don hang  > 0")
    private Long MaDonhang;

    @Min(value=1, message = "Ma san pham > 0")
    @JsonProperty("Ma_Sanpham")
    private Long MaSanpham;

    @Min(value=0, message = "Gia san pham >= 0")
    @JsonProperty("Gia")
    private Float Gia;

    @Min(value=1, message = "So luong san pham  >= 1")
    @JsonProperty("Soluong")
    private int Soluong;

    @Min(value=0, message = "Thanh tien >= 0")
    @JsonProperty("Thanhtien")
    private Float Thanhtien;
}
