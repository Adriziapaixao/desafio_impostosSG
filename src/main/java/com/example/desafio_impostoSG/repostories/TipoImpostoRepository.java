package com.example.desafio_impostoSG.repostories;

import com.example.desafio_impostoSG.dtos.TipoImpostoResponse;
import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoImpostoRepository extends JpaRepository<TipoImpostoEntity, Long> {
    TipoImpostoResponse findAllById(Long id);
}
