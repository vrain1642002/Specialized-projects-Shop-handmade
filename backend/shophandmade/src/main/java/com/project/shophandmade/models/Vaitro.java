package com.project.shophandmade.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vaitro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vaitro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ma;

    @Column(name = "Ten", length = 20, nullable = false)
    private String Ten;

    //tao 2 bien de dung
    public  static String ADMIN="ADMIN";
    public  static String KHACHHANG="KHACHHANG";
}
