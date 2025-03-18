package com.example.desafio_impostoSG.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoImpostoRequest {

    private String name;
    private String description;
    private Double rate;

}
