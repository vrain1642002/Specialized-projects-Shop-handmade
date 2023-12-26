package com.project.shophandmade.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.project.shophandmade.models.Chitietdonhang;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChitietdonhangResponse {
    private Long Ma;

    @JsonProperty("Ma_donhang")
    private Long Ma_donhang;

    @JsonProperty("Ma_Sanpham")
    private Long Ma_Sanpham;

    @JsonProperty("Gia")
    private Float Gia;

    @JsonProperty("Soluong")
    private int Soluong;

    @JsonProperty("Thanhtien")
    private Float Thanhtien;


    public static ChitietdonhangResponse fromchitietdonhang(Chitietdonhang chitietdonhang) {
        return ChitietdonhangResponse
                .builder()
                .Ma(chitietdonhang.getMa())
                .Ma_donhang(chitietdonhang.getDonhang().getMa())
                .Ma_Sanpham(chitietdonhang.getSanpham().getMa())
                .Gia(chitietdonhang.getGia())
                .Soluong(chitietdonhang.getSoluong())
                .Thanhtien(chitietdonhang.getThanhtien())
                 .build();
    }
}
