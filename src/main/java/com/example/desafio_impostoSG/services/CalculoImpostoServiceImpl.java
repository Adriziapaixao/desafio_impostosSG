package com.example.desafio_impostoSG.services;


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

        if (valorBase <= 0) {
            throw new IllegalArgumentException("O valor base deve ser maior que zero.");
        }
        // Busca o tipo de imposto no repositório
        TipoImpostoEntity tipoImpostoEntity = tipoImpostoRepository.findById(tipoImpostoId)
                .orElseThrow(() -> new RuntimeException("Tipo de imposto não encontrado"));

        // Obtém a alíquota do tipo de imposto
        double aliquota = tipoImpostoEntity.getRate();
        double valorImposto = valorBase * (aliquota / 100);

        // Retorna o objeto do tipo CalculoImpostoResponseDto
        return CalculoImpostoResponseDto.builder()
                .tipoImposto(tipoImpostoEntity.getName()) // Nome do imposto
                .valorBase(valorBase)
                .rate(aliquota)
                .valorImposto(valorImposto)
                .build();
    }

}
