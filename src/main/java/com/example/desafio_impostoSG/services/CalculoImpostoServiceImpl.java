package com.example.desafio_impostoSG.services;

import com.example.desafio_impostoSG.dtos.CalculoImpostoRequest;
import com.example.desafio_impostoSG.dtos.CalculoImpostoResponseDto;
import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import com.example.desafio_impostoSG.repostories.TipoImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculoImpostoServiceImpl implements CalculoImpostoService{

    @Autowired
    private TipoImpostoRepository tipoImpostoRepository;

    @Override
    public CalculoImpostoResponseDto calcularImpostoDetalhado(Long tipoImpostoId, double valorBase) {

        String tipoImposto = "ICMS";
        double aliquota = 0.18;
        double valorImposto = valorBase * aliquota;

        return CalculoImpostoResponseDto.builder()
                .tipoImposto(tipoImposto)
                .valorBase(valorBase)
                .aliquota(aliquota)
                .valorImposto(valorImposto)
                .build();
    }

}
