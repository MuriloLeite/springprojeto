package com.site.springprojeto.controller;

import com.site.springprojeto.models.entity.Desenvolvedor;
import com.site.springprojeto.models.repository.DesenvolvedorRepository;
import com.site.springprojeto.models.repository.JogoRepository;
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

    public DesenvolvedorController(DesenvolvedorService desenvolvedorService) {
        this.desenvolvedorService = desenvolvedorService;
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return ResponseEntity.ok(desenvolvedorService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(desenvolvedorService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody Desenvolvedor desenvolvedor) {
        try {
            return ResponseEntity.ok(desenvolvedorService.save(desenvolvedor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity edit(@RequestBody Desenvolvedor desenvolvedor) {
        try {
            return ResponseEntity.ok(desenvolvedorService.save(desenvolvedor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(desenvolvedorService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/total")
    public ResponseEntity getTotal() {
        return ResponseEntity.ok(desenvolvedorService.count());
    }

}
