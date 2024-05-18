package com.site.springprojeto.service;

import com.site.springprojeto.models.entity.Desenvolvedor;
import com.site.springprojeto.models.repository.DesenvolvedorRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DesenvolvedorService {

    private final DesenvolvedorRepository desenvolvedorRepository;

    public DesenvolvedorService(DesenvolvedorRepository desenvolvedorRepository){
        this.desenvolvedorRepository = desenvolvedorRepository;
    }

    public List<Desenvolvedor> findAll() {
        return desenvolvedorRepository.findAll();
    }

    public Desenvolvedor findById(Long id) throws Exception{
        Optional<Desenvolvedor> desenvolvedor = desenvolvedorRepository.findById(id);
        if (!desenvolvedor.isPresent()){
            throw new Exception("Desenvolvedor não encontrado");
        }
        return desenvolvedor.get();
    }

    public Desenvolvedor save(Desenvolvedor desenvolvedor) throws Exception {

        if (desenvolvedor.getCNPJ() == null || desenvolvedor.getCNPJ().length() > 14 || desenvolvedor.getCNPJ().length() < 14){
            throw new Exception("CNPJ não é valido ou esta  incorreto");
        }

        if (desenvolvedor.getNome() == null || desenvolvedor.getNome().length() > 50){
            throw new Exception("O nome deve ter no minimo 1 caractere e maximo de 50");
        }

        if(desenvolvedor.getNota() == null || desenvolvedor.getNota() > 5 || desenvolvedor.getNota() < 5) {
            throw new Exception("A nota deve ser um numero entre 1 e 5");
        }

        if (desenvolvedor.getPresidente() == null){
            throw new Exception("Deve no minimo 1 Presidente");
        }

        if (desenvolvedor.getNumeroColaboradores() == null || desenvolvedor.getNumeroColaboradores() <= 0) {
            throw new Exception("O numero de colaboradores deve ser no minimo 1");
        }

        if (desenvolvedor.getWebsite() == null){
            throw new Exception("Deve haver um Website");
        }

        desenvolvedor.setDataCriacao(new Date());
        return desenvolvedorRepository.save(desenvolvedor);
    }

    public Desenvolvedor delete(Long id) throws Exception {
        Optional<Desenvolvedor> desenvolvedor = desenvolvedorRepository.findById(id);

        if (!desenvolvedor.isPresent()) {
            throw new Exception("Desenvolvedor não encontrado");
        }

        desenvolvedorRepository.delete(desenvolvedor.get());
        return desenvolvedor.get();
    }
    public Long count() {
        return desenvolvedorRepository.count();
    }
}
