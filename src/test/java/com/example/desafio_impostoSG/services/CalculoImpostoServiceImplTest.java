package com.example.desafio_impostoSG.services;


import com.example.desafio_impostoSG.dtos.CalculoImpostoResponseDto;
import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import com.example.desafio_impostoSG.repostories.CalculoImpostoRepository;

import com.example.desafio_impostoSG.repostories.TipoImpostoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculoImpostoServiceImplTest {

    @Mock
    private CalculoImpostoRepository calculoRepository;

    @Mock
    private TipoImpostoRepository tipoImpostoRepository;

    @InjectMocks
    CalculoImpostoServiceImpl calculoImpostoService;

    @Test
    void given_valorBase_whenValorBaseMenorIgualaZero_thenLancaIllegalArgumentException()  {

        Long tipoImpostoId = 1L;
        double valorBase = 0.0;


        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculoImpostoService.calcularImpostoDetalhado( 1L, 0.0);
        });

        Assertions.assertEquals("O valor base deve ser maior que zero.", exception.getMessage());

        verifyNoMoreInteractions(calculoRepository);
    }

    @Test
    void given_tipoImpostoId_whenTipoImpostoNaoEncontrado_thenLancaRuntimeException() {

        Long tipoImpostoId = 1L;
        double valorBase = 150.0;

        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            calculoImpostoService.calcularImpostoDetalhado(tipoImpostoId, valorBase);
        });

        Assertions.assertEquals("Tipo de imposto não encontrado", exception.getMessage());

        verify(tipoImpostoRepository).findById(tipoImpostoId);
        verifyNoMoreInteractions(tipoImpostoRepository);

    }

    @Test
    void given_valorBaseAndTipoImpostoId_whenValid_thenCalculaImpostoCorretamente() {
        Long tipoImpostoId = 1L;
        double valorBase = 100.0;

        TipoImpostoEntity impostoEntity = new TipoImpostoEntity();
        impostoEntity.setId(1L);
        impostoEntity.setName("ICMS");
        impostoEntity.setRate(18.0);

        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.of(impostoEntity));
        CalculoImpostoResponseDto calculoImpostoResponse = calculoImpostoService.calcularImpostoDetalhado(tipoImpostoId, valorBase);

        Assertions.assertNotNull(calculoImpostoResponse);
        Assertions.assertEquals("ICMS", calculoImpostoResponse.getTipoImposto());
        Assertions.assertEquals(100.0, calculoImpostoResponse.getValorBase());
        Assertions.assertEquals(18.0, calculoImpostoResponse.getRate());
        Assertions.assertEquals(18.0, calculoImpostoResponse.getValorImposto());

        verify(tipoImpostoRepository).findById(tipoImpostoId);
        verifyNoMoreInteractions(tipoImpostoRepository);
    }
}