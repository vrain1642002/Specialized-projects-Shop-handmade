package com.project.shophandmade.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sanpham_hinh")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sanpham_hinh {
    public  static  final int soluongMax=4;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ma;

    @ManyToOne
    @JoinColumn(name = "Ma_Sanpham")
    private Sanpham sanpham;

    @Column(name = "Diachihinh", length = 100)
    private String Diachihinh;

}
