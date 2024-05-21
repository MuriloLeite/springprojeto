package com.site.springprojeto.service;

import com.site.springprojeto.models.entity.Desenvolvedor;
import com.site.springprojeto.models.repository.DesenvolvedorRepository;
import com.site.springprojeto.verificar.CnpjValidator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    public Desenvolvedor findById(Long id) throws Exception {
        Optional<Desenvolvedor> desenvolvedor = desenvolvedorRepository.findById(id);
        if (!desenvolvedor.isPresent()){
            throw new Exception("Desenvolvedor não encontrado");
        }
        return desenvolvedor.get();
    }

    public Desenvolvedor save(Desenvolvedor desenvolvedor) throws Exception {
        if (desenvolvedor.getCNPJ() == null || !CnpjValidator.isValid(desenvolvedor.getCNPJ())){
            throw new Exception("CNPJ não é válido ou está incorreto");
        }

        if (desenvolvedor.getNome() == null || desenvolvedor.getNome().length() < 1){
            throw new Exception("O nome deve ter no mínimo 1 caractere e máximo de 50");
        }

        if(desenvolvedor.getNota() == null || desenvolvedor.getNota() < 0 || desenvolvedor.getNota() > 5) {
            throw new Exception("A nota deve ser um número entre 0 e 5");
        }

        if (desenvolvedor.getPresidente() == null || desenvolvedor.getPresidente().isEmpty()){
            throw new Exception("Deve haver no mínimo 1 Presidente");
        }

        if (desenvolvedor.getNumeroColaboradores() == null || desenvolvedor.getNumeroColaboradores() <= 0) {
            throw new Exception("O número de colaboradores deve ser no mínimo 1");
        }

        if (desenvolvedor.getWebsite() == null){
            throw new Exception("Deve haver um Website");
        }

        // Verifica se já existe um desenvolvedor com o nome inserido no get
        Optional<Desenvolvedor> existingDesenvolvedor = desenvolvedorRepository.findByNome(desenvolvedor.getNome());
        if (existingDesenvolvedor.isPresent() && !Objects.equals(existingDesenvolvedor.get().getId(), desenvolvedor.getId())) {
            throw new Exception("Já existe um desenvolvedor com esse nome");
        }

        Optional<Desenvolvedor> existingByCnpj = desenvolvedorRepository.findByCNPJ(desenvolvedor.getCNPJ());
        if (existingByCnpj.isPresent() && !Objects.equals(existingByCnpj.get().getId(), desenvolvedor.getId())) {
            throw new Exception("Já existe um desenvolvedor com esse CNPJ");
        }

        // Verifica se a data de criação é antes da atual
        if (desenvolvedor.getDataCriacao() != null && desenvolvedor.getDataCriacao().after(new Date())) {
            throw new Exception("A data de criação não pode ser posterior à data atual");
        }

        // Se não inserir data ela será a atual
        if (desenvolvedor.getDataCriacao() == null) {
            desenvolvedor.setDataCriacao(new Date());
        }

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
