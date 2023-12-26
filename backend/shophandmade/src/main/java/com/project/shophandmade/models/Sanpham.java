package com.project.shophandmade.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sanpham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sanpham extends Lopcha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Ma;

    @Column(name = "Ten", nullable = false, length = 30)
    private String ten;

    @Column(name = "Gia")
    private Float gia;

    //tro den file anh upload
    @Column(name = "Hinhthunho", length = 100)
    private String hinhthunho;

    @Column(name = "Mota")
    private String Mota;

    @ManyToOne
    @JoinColumn(name = "Ma_Danhmucsanpham")
    private Danhmucsanpham danhmucsanpham;
}
