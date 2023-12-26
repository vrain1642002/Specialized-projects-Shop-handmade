package com.project.shophandmade.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.project.shophandmade.models.Chitietdonhang;
import com.project.shophandmade.models.Donhang;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonhangResponse {
    private Long ma;

    @JsonProperty("manguoidung")
    private Long  manguoidung;

    @JsonProperty("hoten_Nguoinhan")
    private String hoten_Nguoinhan;

    @JsonProperty("sdtnguoinhan")
    private String sdtnguoinhan;

    @JsonProperty("diachinhanhang")
    private String diachinhanhang;


    @JsonProperty("ghichu")
    private String ghichu;

    @JsonProperty("trangthaidh")
    private String trangthaidh;

    @JsonProperty("ngaydathang")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDate ngaydathang;

    @JsonProperty("trangthai")
    private boolean trangthai;

    @JsonProperty("tongtien")
    private double tongtien;

    @JsonProperty("phuongthucvanchuyen")
    private String phuongthucvanchuyen;



    @JsonProperty("phuongthucthanhtoan")
    private String phuongthucthanhtoan;

    @JsonProperty("chitietdonhangs")
    private List<Chitietdonhang> chitietdonhangs;

    public static DonhangResponse fromDonhang(Donhang donhang) {
     DonhangResponse donhangResponse=DonhangResponse
             .builder()
             .ma(donhang.getMa())
             .ghichu(donhang.getGhichu())
             .manguoidung(donhang.getNguoidung().getMa())
             .hoten_Nguoinhan(donhang.getHoten_Nguoinhan())
             .sdtnguoinhan(donhang.getSdt_Nguoinhan())
             .diachinhanhang(donhang.getDiachigiaohang())
             .ngaydathang(donhang.getNgaydat())
             .trangthaidh(donhang.getTrangthaiDH())
             .trangthai(donhang.getTrangthai())
             .tongtien(donhang.getTongtien())
             .phuongthucthanhtoan(donhang.getPhuongthucthanhtoan())
             .phuongthucvanchuyen(donhang.getPhuongthucvanchuyen())
             .chitietdonhangs(donhang.getChitietdonhangs())
             .build();

        return donhangResponse;
    }
}