package com.example.desafio_impostoSG.controllers;

import com.example.desafio_impostoSG.dtos.TipoImpostoRequest;
import com.example.desafio_impostoSG.dtos.TipoImpostoResponse;
import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import com.example.desafio_impostoSG.services.TipoImpostoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tipos")
public class TipoImpostoController {

    @Autowired
    private final TipoImpostoService tipoImpostoService;


    @GetMapping
    public ResponseEntity<List<TipoImpostoEntity>> listarTodosTipoImposto() {
        List<TipoImpostoEntity> tipoImpostoEntities = tipoImpostoService.listarTodosTipoImposto();
        return ResponseEntity.ok(tipoImpostoService.listarTodosTipoImposto());
    }

    @PostMapping
    public ResponseEntity<TipoImpostoResponse> cadastrar(@Valid @RequestBody TipoImpostoRequest tipoImpostoRequest) {

        TipoImpostoResponse tipoImpostoResponse = tipoImpostoService.cadastrar(tipoImpostoRequest).getBody();

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoImpostoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoImpostoResponse> findById(@PathVariable Long id) {
        TipoImpostoResponse tipoImpostoResponse = tipoImpostoService.findById(id);
        return ResponseEntity.ok(tipoImpostoResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tipoImpostoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
