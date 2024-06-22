package com.site.springprojeto.controller;

import com.site.springprojeto.models.entity.Desenvolvedor;
import com.site.springprojeto.service.DesenvolvedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/desenvolvedores")
@CrossOrigin(origins = "http://127.0.0.1:5500")  // Permitir requisições do frontend
public class DesenvolvedorController {

    private final DesenvolvedorService desenvolvedorService;

    @Autowired
    public DesenvolvedorController(DesenvolvedorService desenvolvedorService) {
        this.desenvolvedorService = desenvolvedorService;
    }

    @GetMapping
    public ResponseEntity<List<Desenvolvedor>> findAll() {
        List<Desenvolvedor> desenvolvedores = desenvolvedorService.findAll();
        return ResponseEntity.ok(desenvolvedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Desenvolvedor desenvolvedor = desenvolvedorService.findById(id);
            return ResponseEntity.ok(desenvolvedor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Desenvolvedor desenvolvedor) {
        try {
            Desenvolvedor savedDesenvolvedor = desenvolvedorService.save(desenvolvedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDesenvolvedor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @Valid @RequestBody Desenvolvedor desenvolvedor) {
        try {
            Desenvolvedor existingDesenvolvedor = desenvolvedorService.findById(id);
            if (existingDesenvolvedor == null) {
                return ResponseEntity.notFound().build();
            }
            // Atualiza os campos do desenvolvedor existente com os novos dados
            existingDesenvolvedor.setNome(desenvolvedor.getNome());
            existingDesenvolvedor.setCNPJ(desenvolvedor.getCNPJ());
            existingDesenvolvedor.setNota(desenvolvedor.getNota());
            existingDesenvolvedor.setDataCriacao(desenvolvedor.getDataCriacao());
            existingDesenvolvedor.setPresidente(desenvolvedor.getPresidente());
            existingDesenvolvedor.setNumeroColaboradores(desenvolvedor.getNumeroColaboradores());
            existingDesenvolvedor.setWebsite(desenvolvedor.getWebsite());

            // Salva o desenvolvedor atualizado
            Desenvolvedor updatedDesenvolvedor = desenvolvedorService.save(existingDesenvolvedor);
            return ResponseEntity.ok(updatedDesenvolvedor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            desenvolvedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotal() {
        Long total = desenvolvedorService.count();
        return ResponseEntity.ok(total);
    }
}
