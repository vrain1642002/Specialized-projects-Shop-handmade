package com.project.shophandmade.responses;


import com.fasterxml.jackson.annotation.JsonProperty;

import com.project.shophandmade.models.Danhmucsanpham;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

//tang tra ve ket qua phan hoi de dng cho front end
public class DanhmucsanphamResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("danhmucsanpham")
    private Danhmucsanpham danhmucsanpham;
}