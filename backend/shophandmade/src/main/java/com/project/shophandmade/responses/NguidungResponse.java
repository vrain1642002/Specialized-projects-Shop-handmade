package com.project.shophandmade.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NguidungResponse {
    @JsonProperty("ma")
    private Long ma;

    @JsonProperty("hoten")
    private String hoten;

    @JsonProperty("sdt")
    private String sdt;

    @JsonProperty("diachi")
    private String diachi;

    @JsonProperty("trangthai")
    private boolean trangthai;

    @JsonProperty("ngaysinh")
    private Date ngaysinh;



    @JsonProperty("vaitro")
    private com.project.shophandmade.models.Vaitro vaitro;
    public static NguidungResponse fromNguoidung(com.project.shophandmade.models.Nguoidung nguoidung) {
        return NguidungResponse.builder()
                .ma(nguoidung.getMa())
                .hoten(nguoidung.getHoten())
                .sdt(nguoidung.getHoten())
                .diachi(nguoidung.getDiachi())
                .sdt(nguoidung.getSdt())
                .trangthai(nguoidung.isTrangthai())
                .ngaysinh(nguoidung.getNgaysinh())
                .vaitro(nguoidung.getVaitro())
                .build();

    }
}
