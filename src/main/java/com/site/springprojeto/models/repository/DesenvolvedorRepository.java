package com.site.springprojeto.models.repository;

import com.site.springprojeto.models.entity.Desenvolvedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long> {
    Optional<Desenvolvedor> findByNome(String nome);
    Optional<Desenvolvedor> findByCNPJ(String cnpj);
}
