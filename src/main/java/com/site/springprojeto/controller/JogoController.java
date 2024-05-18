package com.site.springprojeto.controller;

import com.site.springprojeto.models.entity.Desenvolvedor;
import com.site.springprojeto.models.entity.Jogo;
import com.site.springprojeto.models.repository.JogoRepository;
import com.site.springprojeto.models.repository.JogoRepository;
import com.site.springprojeto.service.DesenvolvedorService;
import com.site.springprojeto.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/desenvolvedores")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return ResponseEntity.ok(jogoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(jogoService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody Jogo jogo) {
        try {
            return ResponseEntity.ok(jogoService.save(jogo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity edit(@RequestBody Jogo jogo) {
        try {
            return ResponseEntity.ok(jogoService.save(jogo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(jogoService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/total")
    public ResponseEntity getTotal() {
        return ResponseEntity.ok(jogoService.count());
    }

}
