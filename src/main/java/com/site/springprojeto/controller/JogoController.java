package com.site.springprojeto.controller;

import com.site.springprojeto.models.entity.Jogo;
import com.site.springprojeto.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private final JogoService jogoService;

    @Autowired
    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> findAll() {
        List<Jogo> jogos = jogoService.findAll();
        return ResponseEntity.ok(jogos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> findById(@PathVariable("id") Long id) {
        try {
            Jogo jogo = jogoService.findById(id);
            return ResponseEntity.ok(jogo);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Jogo> save(@Valid @RequestBody Jogo jogo) {
        try {
            Jogo savedJogo = jogoService.save(jogo);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedJogo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Jogo> edit(@Valid @RequestBody Jogo jogo) {
        try {
            // O ID já está no objeto 'jogo' passado no corpo da requisição
            Jogo updatedJogo = jogoService.save(jogo);
            return ResponseEntity.ok(updatedJogo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            jogoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotal() {
        Long total = jogoService.count();
        return ResponseEntity.ok(total);
    }
}
