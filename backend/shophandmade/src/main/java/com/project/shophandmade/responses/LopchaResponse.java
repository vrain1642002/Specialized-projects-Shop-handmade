package com.project.shophandmade.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
//Luu tru thong tin dinh nghia cac doi tuong tra ve
public class LopchaResponse {
    @JsonProperty("Ngaytao")
    private LocalDateTime ngaytao;

    @JsonProperty("Ngaycapnhat")
    private LocalDateTime ngaycapnhat;
}
