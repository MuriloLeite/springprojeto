package com.site.springprojeto.service;

import com.site.springprojeto.models.entity.Jogo;
import com.site.springprojeto.models.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;}

    public List<Jogo> findAll(){
        return jogoRepository.findAll();
    }
    public Jogo findById(Long id) throws Exception{
        Optional<Jogo> jogo = jogoRepository.findById(id);
        if(!jogo.isPresent()){
            throw new Exception("Jogo não encontrado");
        }
        return jogo.get();
    }

    public Jogo save(Jogo jogo) throws Exception{
        if (jogo.getNome() == null || jogo.getNome().length() > 1) {
            throw new Exception("Nome deve ser maior ou igual a 1 caracteres");
        }

        if (jogo.getPreco() == null || jogo.getPreco() < 0) {
            throw new Exception("O preço deve ser no minimo o valor 0");
        }

        if (jogo.getImagens() == null){
            throw new Exception("Deve haver uma imagem");
        }

        if(jogo.getDescricao() == null || jogo.getDescricao().length() > 400){
            throw new Exception("A Descrição deve ter no minimo 1 caracter e no maximo 400");
        }

        if(jogo.getNota() == null || jogo.getNota() > 5 || jogo.getNota() < 0) {
            throw new Exception("A nota deve ser um numero entre 0 e 5");
        }

        if(jogo.getClassificacao() == null){
            throw new Exception("A Classificação deve estar entre as classificações do Brasil (10, 12, 14, 16, 18 ou Livre)");
        }

        if(jogo.getGenero() == null) {
            throw new Exception("Você deve escolher pelo menos um genero para o jogo");
        }

        jogo.setDataLancamento(new Date());

        return jogoRepository.save(jogo);

    }

    public Jogo delete(Long id) throws Exception {
        Optional<Jogo> jogo = jogoRepository.findById(id);

        if (!jogo.isPresent()) {
            throw new Exception("Jogo não encontrado");
        }

        jogoRepository.delete(jogo.get());
        return jogo.get();
    }

    public Long count(){
        return jogoRepository.count();
    }
}
