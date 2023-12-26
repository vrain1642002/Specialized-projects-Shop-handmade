package com.project.shophandmade.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "donhang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Donhang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Ma;

    @ManyToOne
    @JoinColumn(name = "Ma_Nguoidung")
    private Nguoidung nguoidung;

    @Column(name = "Hoten_Nguoinhan", length = 30)
    private String Hoten_Nguoinhan;


    @Column(name = "SDT_Nguoinhan",nullable = false, length = 10)
    private String sdt_Nguoinhan;


    @Column(name = "Ghichu", length = 100)
    private String Ghichu;

    @Column(name="Ngaydat")
    private LocalDate Ngaydat;

    @Column(name = "TrangthaiDH")
    private String TrangthaiDH;

    @Column(name = "Tongtien")
    private float Tongtien;

    @Column(name = "Phuongthucvanchuyen",length = 30)
    private String Phuongthucvanchuyen;

    @Column(name = "Diachigiaohang")
    private String Diachigiaohang;




    @Column(name = "Phuongthucthanhtoan")
    private String Phuongthucthanhtoan;

    @Column(name = "Trangthai")
    private Boolean Trangthai;//thuộc về admin

    @OneToMany(mappedBy = "donhang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Chitietdonhang> chitietdonhangs;

}
