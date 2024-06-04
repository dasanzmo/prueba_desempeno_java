package com.riwi.prueba_desempeno.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReponse {
    private Long id;
    private String name;
    private String email;
    private boolean active;
}
