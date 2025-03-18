package com.example.desafio_impostoSG.services;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.desafio_impostoSG.dtos.TipoImpostoRequest;
import com.example.desafio_impostoSG.dtos.TipoImpostoResponse;
import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipoImpostoService {
    
    List<TipoImpostoEntity> listarTodos();

    ResponseEntity<TipoImpostoResponse> cadastrar(TipoImpostoRequest tipoImpostoRequest);

    TipoImpostoResponse findById(Long id);

    void deleteById(Long id);
}
