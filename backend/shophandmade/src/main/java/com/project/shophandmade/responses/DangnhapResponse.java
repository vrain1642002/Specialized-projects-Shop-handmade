package com.project.shophandmade.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DangnhapResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("token")
    private String token;
}
