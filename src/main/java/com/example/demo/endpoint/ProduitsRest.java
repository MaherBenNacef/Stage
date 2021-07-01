package com.example.demo.endpoint;


import com.example.demo.dto.DtoProduit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.demo.services.ProduitsService;
import com.example.demo.models.Produits;
import com.example.demo.repositories.ProduitsRepositories;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/produits")
public class ProduitsRest {

    private final ProduitsService service;
    private final ProduitsRepositories repositories;
    private final ModelMapper mapper;


    @Autowired
    public ProduitsRest(ProduitsService service,ProduitsRepositories repositories,ModelMapper mapper) {
        this.service = service;
        this.repositories=repositories;
        this.mapper=mapper;

    }

    @GetMapping
    public List<Produits> getall(){
        return repositories.findAll();
    }
    @GetMapping("/{id}")
    public Produits findById(@PathVariable("id") long id){
        Optional<Produits> produits = repositories.findById(id);
        return produits.orElseThrow(()->new NoSuchElementException());
    }
    @PostMapping("/{id}")
    public Produits create(@RequestBody DtoProduit produits , @PathVariable("id") long id){
        return service.createProduits(mapper.map(produits,Produits.class),id);
    }
    @PutMapping("/{id}")
    public Produits updateProduits(@PathVariable("id") long id , @RequestBody DtoProduit produits ){
        return service.updateProduits(id,mapper.map(produits,Produits.class));
    }

    @GetMapping("/byCategorie/{id}")
    public List<Produits> getByCategorie(@PathVariable(value = "id") Long idCategorie)throws NoSuchElementException{
        return repositories.findAll()
                .stream()
                .filter(m->m.getCategories().getId()==idCategorie)
                //.filter(p->p.getCategories().getId()==idCategorie)
                .collect(Collectors.toList());

    }

   @DeleteMapping("/{id}")
   public String deleteproduit(@PathVariable(value = "id") Long produitId){
       var p = repositories.findById(produitId);
        repositories.delete(p.orElseThrow(()->new NoSuchElementException()));
       return "Produit was deleted !";
   }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


}
