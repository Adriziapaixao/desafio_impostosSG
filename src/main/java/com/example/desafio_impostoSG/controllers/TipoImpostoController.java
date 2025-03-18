package com.example.desafio_impostoSG.controllers;

import com.example.desafio_impostoSG.dtos.TipoImpostoRequest;
import com.example.desafio_impostoSG.dtos.TipoImpostoResponse;
import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import com.example.desafio_impostoSG.services.TipoImpostoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/tipos")
public class TipoImpostoController {

    @Autowired
    private TipoImpostoService tipoImpostoService;


    @GetMapping
    public List<TipoImpostoEntity> listarTodos() {
        return tipoImpostoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<TipoImpostoResponse> cadastrar(@RequestBody TipoImpostoRequest tipoImpostoRequest) {
        // Chama o serviço para cadastrar o tipo de imposto
        ResponseEntity<TipoImpostoResponse> responseEntity = tipoImpostoService.cadastrar(tipoImpostoRequest);

        // Extrai o corpo do ResponseEntity
        TipoImpostoResponse tipoImpostoResponse = responseEntity.getBody();

        // Retorna o ResponseEntity diretamente, já que ele já está formatado corretamente
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoImpostoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoImpostoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoImpostoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        tipoImpostoService.deleteById(id);
    }

    // Tratamento de exceções de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
