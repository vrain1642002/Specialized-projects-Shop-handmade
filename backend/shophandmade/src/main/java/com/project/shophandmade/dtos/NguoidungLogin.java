package com.project.shophandmade.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NguoidungLogin {
    @JsonProperty("SDT")
    @NotBlank(message = "SDT la can thiet")
    private String SDT;

    @JsonProperty("Matkhau")
    @NotBlank(message = "Mat khau khong duoc de trong")
    private String Matkhau;

}
