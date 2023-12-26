package com.project.shophandmade.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DonhangDTO {
    //dinh nghia trung voi truong trong DB
    @JsonProperty("Ma_Nguoidung")
    @Min(value = 1, message = "Ma nguoi dung phai lon hon 0")
    private Long MaNguoidung;

    @JsonProperty("Hoten_Nguoinhan")
    private String HotenNguoinhan;


    //khong nhat thiet phai giong user
    @Size(min = 10, message = "SDT phai 10 ki tu")
    @JsonProperty("SDT_Nguoinhan")
    private String SDTNguoinhan;


    @JsonProperty("Ghichu")
    private String Ghichu;

    @JsonProperty("Tongtien")
    @Min(value = 0, message = "Tong tien phai >= 0")
    private Float Tongtien;

    @JsonProperty("Phuongthucvanchuyen")
    private String Phuongthucvanchuyen;

    @JsonProperty("Diachigiaohang")
    private String Diachigiaohang;

    @JsonProperty("Phuongthucthanhtoan")
    private String Phuongthucthanhtoan;

    @Column(name = "TrangthaiDH")
    private String TrangthaiDH;

    @Column(name = "Trangthai")
    private Boolean Trangthai;

    @JsonProperty("cart_items")
    private List<CartItemDTO> cartItems;

}
