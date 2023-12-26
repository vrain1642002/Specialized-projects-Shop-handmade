package com.project.shophandmade.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanphamDTO {
    @NotBlank(message = "Ten khong duoc de trong")
    @Size(min = 3, max = 30, message = "Ten phai dai hon 3 va nho hon 30 ki tu")
    private String Ten;

    @Min(value = 0, message = "Gia phai lon hon 0")
    @Max(value = 10000000, message = "Gia phai nho hon hoac bang 10,000,000")
    private Float Gia;

    private String Hinhthunho;

    private String Mota;

    @JsonProperty("Ma_Danhmucsanpham")
    private Long Madanhmucsanpham;



}
