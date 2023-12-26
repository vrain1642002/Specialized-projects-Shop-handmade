package com.project.shophandmade.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapnhatnguoidungDTO {
    @JsonProperty("hoten")
    private String hoten;

    @JsonProperty("diachi")
    private String diachi;

    @JsonProperty("matkhau")
    private String matkhau;

    @JsonProperty("nhaplaimatkhau")
    private String nhaplaimatkhau;

    @JsonProperty("ngaysinh")
    private Date ngaysinh;

}
