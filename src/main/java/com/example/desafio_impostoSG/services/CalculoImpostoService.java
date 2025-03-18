package com.example.desafio_impostoSG.services;

import com.example.desafio_impostoSG.dtos.CalculoImpostoRequest;
import com.example.desafio_impostoSG.dtos.CalculoImpostoResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CalculoImpostoService {

    CalculoImpostoResponseDto calcularImpostoDetalhado(Long tipoImpostoId, double valorBase);

}
