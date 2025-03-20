package com.example.desafio_impostoSG.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculoImpostoRequest {

    @NotNull(message = "O campo 'tipoImpostoId' é obrigatório.")
    private Long tipoImpostoId;

    @NotNull(message = "O campo 'valorBase' é obrigatório.")
    private Double valorBase;

}
