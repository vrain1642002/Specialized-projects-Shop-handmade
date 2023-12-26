package com.project.shophandmade.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shophandmade.models.Sanpham;
import com.project.shophandmade.models.Sanpham_hinh;
import com.project.shophandmade.repositories.Sanpham_hinhRepository;
import com.project.shophandmade.services.ISanphamService;
import com.project.shophandmade.services.SanphamService;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SanphamResponse extends LopchaResponse {

    private Long Ma;
    private String Ten;
    private Float Gia;
    private String Hinhthunho;
    private String Mota;
    private Long Madanhmucsanpham;
    private List<Sanpham_hinh>   sanpham_hinhs;
    public static SanphamResponse tuSanpham(Sanpham sanpham) {
        SanphamResponse sanphamResponse = SanphamResponse.builder()
                .Ma(sanpham.getMa())
                .Ten(sanpham.getTen())
                .Gia(sanpham.getGia())
                .Hinhthunho(sanpham.getHinhthunho())
                .Mota(sanpham.getMota())
                .Madanhmucsanpham(sanpham.getDanhmucsanpham().getMa())
                .build();

        sanphamResponse.setNgaytao(sanpham.getNgaytao());
        sanphamResponse.setNgaycapnhat(sanpham.getNgaycapnhat());
        return sanphamResponse;
    }

}
