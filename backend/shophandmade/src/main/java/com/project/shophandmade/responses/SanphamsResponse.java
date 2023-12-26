package com.project.shophandmade.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class SanphamsResponse {
    private List<SanphamResponse> sanphams;
    private int tongtrangs;
}
