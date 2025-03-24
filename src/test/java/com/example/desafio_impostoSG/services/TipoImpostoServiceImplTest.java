package com.example.desafio_impostoSG.services;

import com.example.desafio_impostoSG.dtos.TipoImpostoRequest;
import com.example.desafio_impostoSG.dtos.TipoImpostoResponse;
import com.example.desafio_impostoSG.models.RoleEntity;
import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import com.example.desafio_impostoSG.repostories.TipoImpostoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;


@ExtendWith(MockitoExtension.class)
class TipoImpostoServiceImplTest {

    @Mock
    private TipoImpostoRepository tipoImpostoRepository;

    @InjectMocks
    private TipoImpostoServiceImpl tipoImpostoService;

    /*
     * given_método_when_cenário_then_retornoEsperado
     */

    @Test
    void given_cadastrar_whenCadastrarTipoImposto_thenCadastroRealizado() {

        TipoImpostoRequest tipoImpostoRequest = TipoImpostoRequest.builder()
                .name("ICMS")
                .description("Imposto sobre circulação de mercadorias e serviços")
                .rate(18.0)
                .build();

        TipoImpostoEntity tipoImpostoEntity = TipoImpostoEntity.builder()
                .id(1L)
                .name("ICMS")
                .description("Imposto sobre circulação de mercadorias e serviços")
                .rate(18.0)
                .build();

        when(tipoImpostoRepository.save(any(TipoImpostoEntity.class))).thenReturn(tipoImpostoEntity);

        ResponseEntity<TipoImpostoResponse> response = tipoImpostoService.cadastrar(tipoImpostoRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("ICMS", response.getBody().getName());
        assertEquals("Imposto sobre circulação de mercadorias e serviços", response.getBody().getDescription());
        assertEquals(18.0, response.getBody().getRate());

    }

    @Test
    void given_findById_whenIdExistente_thenRetornaEntidade() {
        TipoImpostoEntity tipoImpostoEntity = new TipoImpostoEntity();
                tipoImpostoEntity.setId(1L);
                tipoImpostoEntity.setName("ISS");
                tipoImpostoEntity.setDescription("Imposto sobre serviço");
                tipoImpostoEntity.setRate(15.0);

        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.of(tipoImpostoEntity));

        TipoImpostoResponse response = tipoImpostoService.findById(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getId());
        Assertions.assertEquals("ISS", response.getName());
        Assertions.assertEquals("Imposto sobre serviço", response.getDescription());
        Assertions.assertEquals(15.0, response.getRate());

        Mockito.verify(tipoImpostoRepository).findById(1L);

    }

    @Test
    void given_findById_whenIdInexistente_thenLancaEntityNotFoundException() {

        Long idInexistente = 30L;

        when(tipoImpostoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> tipoImpostoService.findById(idInexistente));

        Assertions.assertEquals("Tipo de imposto não encontrado com o ID: " + idInexistente, exception.getMessage());

        Mockito.verify(tipoImpostoRepository).findById(idInexistente);
    }

    @Test
    void given_deleteById_whenIdExistente_thendeletaEntidade() {

        Long idExistente = 1L;

        when(tipoImpostoRepository.existsById(1L)).thenReturn(true);

        tipoImpostoService.deleteById(idExistente);
        verify(tipoImpostoRepository).existsById(idExistente);
        verify(tipoImpostoRepository).deleteById(idExistente);
    }

    @Test
    void given_deleteById_whenIdInexistente_thenLancaEntityNotFoundException() {

        Long idInexistente = 30L;

        when(tipoImpostoRepository.existsById(idInexistente)).thenReturn(false);


        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> tipoImpostoService.deleteById(idInexistente)
        );

        Assertions.assertEquals("Tipo de imposto com ID " + idInexistente + " não encontrado.", exception.getMessage());


        verify(tipoImpostoRepository).existsById(idInexistente);

        verify(tipoImpostoRepository, Mockito.never()).deleteById(idInexistente);

    }

    @Test
    void given_listarTodosTipoImposto_whenListarTodosTipoImposto_thenRetornaListaDeInposto() {
        List<TipoImpostoEntity> tipoImpostoList = new ArrayList<>();
        TipoImpostoEntity tipoImpostoEntity1 = new TipoImpostoEntity(1L, "ICMS", "Imposto sobre circulação de mercadorias e serviços", 18.0);
        TipoImpostoEntity tipoImpostoEntity2 = new TipoImpostoEntity(2L, "ISS", "Imposto sobre Serviços", 15.0);

        tipoImpostoList.add(tipoImpostoEntity1);
        tipoImpostoList.add(tipoImpostoEntity2);

        when(tipoImpostoService.listarTodosTipoImposto()).thenReturn(tipoImpostoList);
        var result = tipoImpostoService.listarTodosTipoImposto();

        Assertions.assertNotEquals(0, result.size());
        assertEquals(2, result.size());

    }
}