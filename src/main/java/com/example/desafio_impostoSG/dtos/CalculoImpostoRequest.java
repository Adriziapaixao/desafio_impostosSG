package com.example.desafio_impostoSG.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculoImpostoRequest {

    private Long tipoImpostoId;
    private Double ValorBase;

}
