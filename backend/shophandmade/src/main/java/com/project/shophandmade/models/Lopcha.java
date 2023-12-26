package com.project.shophandmade.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class Lopcha {
    @Column(name = "Ngaytao")
    private LocalDateTime ngaytao;

    @Column(name = "Ngaycapnhat")
    private LocalDateTime ngaycapnhat;

    //lang nghe su kien khi luu doi tuong vao CSDL
    @PrePersist
    protected void onCreate() {
        ngaytao = LocalDateTime.now();
        ngaycapnhat = LocalDateTime.now();
    }

    //khi cap nhat CSDL
    @PreUpdate
    protected void onUpdate() {
        ngaycapnhat = LocalDateTime.now();
    }

}
