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

    @PutMapping
    public ResponseEntity<?> edit(@Valid @RequestBody Desenvolvedor desenvolvedor) {
        try {
            Desenvolvedor updatedDesenvolvedor = desenvolvedorService.save(desenvolvedor);
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
