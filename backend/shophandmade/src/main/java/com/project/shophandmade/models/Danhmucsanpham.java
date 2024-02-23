package com.project.shophandmade.models;

import jakarta.persistence.*;
import lombok.*;
//LA thuc the
@Entity
//Anh xa voi table trong csdl
@Table(name = "danhmucsanpham")
@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Danhmucsanpham {
    //khoa chinh tu dong tanng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ma;

    //anh xa cot
    @Column(name = "Ten", nullable = false)
    private String ten;
}
