package com.project.shophandmade.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shophandmade.models.Nguoidung;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DangkiResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("nguoidung")
    private Nguoidung nguoidung;
}
