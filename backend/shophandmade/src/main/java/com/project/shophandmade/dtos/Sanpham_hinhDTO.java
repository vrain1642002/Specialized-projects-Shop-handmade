package com.project.shophandmade.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sanpham_hinhDTO {
    @JsonProperty("Ma_Sanpham")
    @Min(value = 1, message = "Ma san pham phai lon hon 0")
    private Long Ma_Sanpham;

    @Size(min = 5, max = 200, message = "dia chi hinh")
    @JsonProperty("Diachihinh")
    private String Diachihinh;
}
