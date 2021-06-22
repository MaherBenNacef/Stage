package com.example.demo.EndPoint;

import com.example.demo.Services.ProduitsService;
import com.example.demo.models.Produits;
import com.example.demo.repositories.ProduitsRepositories;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/produits")
public class ProduitsRest {

    private ProduitsService service;
    private ProduitsRepositories repositories;

    @Autowired
    public ProduitsRest(ProduitsService service,ProduitsRepositories repositories) {
        this.service = service;
        this.repositories=repositories;
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
    /*@DeleteMapping("/{id}")
    public Produits deleteProduits(@PathVariable("id") long id){
        return service.deleteProduits(id);
    }*/

   /*@GetMapping("/list/{id}")
    public List<Produits> findByCategorie(@PathVariable("id") long id){
        return repositories.findByCategories_Id(id);
    }*/
   /*@GetMapping("prod/{id}")
   public List<Produits> getProdByCateg(@PathVariable("id")long id){
       return repositories.findByCategories_Id(id);
   }*/

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public String deleteproduit(@PathVariable(value = "id") Long produitId) throws Exception {
        /*Produits produit = repositories.findById(produitId)
                .orElseThrow(() -> new Exception("produit not found for this id :: " + produitId) {
                });*/
        repositories.delete(repositories.findById(produitId).get());
        //Map<String, Boolean> response = new HashMap<>();
        //response.put("deleted", Boolean.TRUE);
        return "Produit was deleted !";
    }
}
