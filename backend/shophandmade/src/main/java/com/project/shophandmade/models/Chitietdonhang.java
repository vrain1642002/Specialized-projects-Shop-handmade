package com.project.shophandmade.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chitietdonhang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chitietdonhang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ma;

    @ManyToOne
    @JoinColumn(name = "Ma_Donhang")
    @JsonBackReference
    private Donhang donhang;

    @ManyToOne
    @JoinColumn(name = "Ma_Sanpham")
    private Sanpham sanpham;

    @Column(name = "Gia", nullable = false)
    private Float Gia;

    @Column(name = "Soluong", nullable = false)
    private int Soluong;

    @Column(name = "Thanhtien", nullable = false)
    private Float Thanhtien;

   

}
