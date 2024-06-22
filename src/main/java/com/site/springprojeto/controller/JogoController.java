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
@CrossOrigin(origins = "http://127.0.0.1:5500")
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
            if (jogo == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(jogo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Jogo jogo) {
        try {
            Jogo savedJogo = jogoService.save(jogo);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedJogo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @Valid @RequestBody Jogo jogo) {
        try {
            Jogo existingJogo = jogoService.findById(id);
            if (existingJogo == null) {
                return ResponseEntity.notFound().build();
            }
            jogo.setId(id); // Garante que o ID passado na URL seja respeitado
            Jogo updatedJogo = jogoService.save(jogo);
            return ResponseEntity.ok(updatedJogo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            jogoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotal() {
        Long total = jogoService.count();
        return ResponseEntity.ok(total);
    }
}
