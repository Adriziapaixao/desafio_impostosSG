package com.example.desafio_impostoSG.services;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.desafio_impostoSG.dtos.TipoImpostoRequest;
import com.example.desafio_impostoSG.dtos.TipoImpostoResponse;
import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import com.example.desafio_impostoSG.repostories.TipoImpostoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TipoImpostoServiceImpl implements TipoImpostoService{

    @Autowired
    private final TipoImpostoRepository tipoImpostoRepository;

    public TipoImpostoServiceImpl(TipoImpostoRepository tipoImpostoRepository) {
        this.tipoImpostoRepository = tipoImpostoRepository;
    }

    @Override
    public ResponseEntity<TipoImpostoResponse> cadastrar(TipoImpostoRequest tipoImpostoRequest) {
        // Converte o DTO de requisição para a entidade
        TipoImpostoEntity tipoImpostoEntity = TipoImpostoEntity.builder()
                .name(tipoImpostoRequest.getName())
                .description(tipoImpostoRequest.getDescription())
                .rate(tipoImpostoRequest.getRate())
                .build();

        // Salva a entidade no banco de dados
        TipoImpostoEntity savedEntity = tipoImpostoRepository.save(tipoImpostoEntity);

        // Converte a entidade salva para o DTO de resposta
        TipoImpostoResponse tipoImpostoResponse = TipoImpostoResponse.builder()
                .id(savedEntity.getId())
                .name(savedEntity.getName())
                .description(savedEntity.getDescription())
                .rate(savedEntity.getRate())
                .build();

        // Retorna o DTO de resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoImpostoResponse);
    }

    @Override
    public TipoImpostoResponse findById(Long id) {
        return tipoImpostoRepository.findAllById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteById(Long id) {

        if (!tipoImpostoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tipo de imposto com ID " + id + " não encontrado.");
        }

        // Exclui o tipo de imposto pelo ID
        tipoImpostoRepository.deleteById(id);

    }

    @Override
    public List<TipoImpostoEntity> findAll() {
        return tipoImpostoRepository.findAll();
    }


}
