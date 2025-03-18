package com.example.desafio_impostoSG.repostories;

import com.example.desafio_impostoSG.models.TipoImpostoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoImpostoRepository extends JpaRepository<TipoImpostoEntity,Long> {
}
