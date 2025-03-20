package com.example.desafio_impostoSG.controllers;

import com.example.desafio_impostoSG.dtos.CalculoImpostoRequest;
import com.example.desafio_impostoSG.dtos.CalculoImpostoResponseDto;
import com.example.desafio_impostoSG.services.CalculoImpostoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/calculo")
public class CalculoImpostoController {

    @Autowired
    private final CalculoImpostoService calculoImpostoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> calcularImposto(@Valid @RequestBody CalculoImpostoRequest request) {
        if (request.getValorBase() == null) {
            return ResponseEntity.badRequest().body("O campo 'valorBase' não pode ser nulo.");
        }

        CalculoImpostoResponseDto response = calculoImpostoService
                .calcularImpostoDetalhado(request.getTipoImpostoId(), request.getValorBase());
        return ResponseEntity.ok(response);
    }
}
