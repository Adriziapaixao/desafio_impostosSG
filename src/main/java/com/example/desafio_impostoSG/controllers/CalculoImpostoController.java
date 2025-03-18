package com.example.desafio_impostoSG.controllers;

import com.example.desafio_impostoSG.dtos.CalculoImpostoRequest;
import com.example.desafio_impostoSG.dtos.TipoImpostoRequest;
import com.example.desafio_impostoSG.dtos.CalculoImpostoResponseDto;
import com.example.desafio_impostoSG.services.CalculoImpostoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/calculo")
public class CalculoImpostoController {

    @Autowired
    private CalculoImpostoService calculoImpostoService;

    @PostMapping
    public ResponseEntity<CalculoImpostoResponseDto> calcularImposto(@RequestBody CalculoImpostoRequest request) {
        CalculoImpostoResponseDto response = calculoImpostoService
                .calcularImpostoDetalhado(request.getTipoImpostoId(), request.getValorBase());
        return ResponseEntity.ok(response);
    }
}
