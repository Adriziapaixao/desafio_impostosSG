package com.example.desafio_impostoSG.services;


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
        TipoImpostoEntity tipoImpostoEntity = tipoImpostoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de imposto não encontrado com o ID: " + id));

        // Converte a entidade para o DTO de resposta
        return new TipoImpostoResponse(
                tipoImpostoEntity.getId(),
                tipoImpostoEntity.getName(),
                tipoImpostoEntity.getDescription(),
                tipoImpostoEntity.getRate()
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteById(Long id) {

        if (!tipoImpostoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tipo de imposto com ID " + id + " não encontrado.");
        }
        tipoImpostoRepository.deleteById(id);
    }

    @Override
    public List<TipoImpostoEntity> listarTodosTipoImposto() {
        return tipoImpostoRepository.findAll();
    }



}
