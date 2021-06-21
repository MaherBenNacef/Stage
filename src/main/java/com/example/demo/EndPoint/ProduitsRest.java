package com.example.demo.EndPoint;

import com.example.demo.Services.ProduitsService;
import com.example.demo.models.Produits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produits")
public class ProduitsRest {

    private ProduitsService service;

    @Autowired
    public ProduitsRest(ProduitsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produits> getall(){
        return service.getAllProduits();
    }
    @GetMapping("/{id}")
    public Produits findById(@PathVariable("id") long id){
        return service.findProduitsById(id);
    }
    @PostMapping("/{id}")
    public Produits create(@RequestBody Produits produits , @PathVariable("id") long id){
        return service.createProduits(produits,id);
    }
    @PutMapping("/{id}")
    public Produits updateProduits(@PathVariable("id") long id , @RequestBody Produits produits ){
        return service.updateProduits(id,produits);
    }
    @DeleteMapping("/{id}")
    public Produits deleteProduits(@PathVariable("id") long id){
        return service.deleteProduits(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
