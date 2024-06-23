package com.site.springprojeto.models.repository;

import com.site.springprojeto.models.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    List<Jogo> findByDesenvolvedorId(Long devId);
}
