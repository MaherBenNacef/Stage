package com.example.demo.EndPoint;

import com.example.demo.Services.CategorieService;
import com.example.demo.models.Categories;
import com.example.demo.models.Produits;
import com.example.demo.repositories.CategoriesRepositories;
import com.example.demo.repositories.ProduitsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/categories")
public class CategoriesRest {

    private CategorieService service;
    private CategoriesRepositories repositories;
    private ProduitsRepositories produitsRepositories;

    @Autowired
    public CategoriesRest(CategorieService service,CategoriesRepositories repositories,ProduitsRepositories produitsRepositories) {
        this.service = service;
        this.repositories=repositories;
        this.produitsRepositories=produitsRepositories;
    }
    @GetMapping
    public List<Categories> getall(){
        return service.getAllCategories();
    }
    @GetMapping("/{id}")
    public Categories findById(@PathVariable("id") long id){
        return service.findCategoriesById(id);
    }
    @PostMapping
    public Categories create(@RequestBody Categories categories){
        return service.createCategories(categories);
    }
    @PutMapping("/{id}")
    public Categories updateCategories(@PathVariable("id") long id , @RequestBody Categories categories ){
        return service.updateCategories(id,categories);
    }
    @DeleteMapping("/{id}")
    public String deleteCat(@PathVariable("id") long id){
        List<Produits> produitsList = produitsRepositories.findAll();
        for(Produits p : produitsList)
            produitsRepositories.deleteById(p.getId());
        repositories.deleteById(id);
        return "Categorie deleted";
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
