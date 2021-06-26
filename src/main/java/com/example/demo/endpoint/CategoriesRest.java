package com.example.demo.endpoint;

import com.example.demo.services.CategorieService;
import com.example.demo.models.Categories;
import com.example.demo.models.Produits;
import com.example.demo.repositories.CategoriesRepositories;
import com.example.demo.repositories.ProduitsRepositories;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.DtoCategorie;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/categories")
public class CategoriesRest {

    private CategorieService service;
    private CategoriesRepositories repositories;
    private ProduitsRepositories produitsRepositories;
    private ModelMapper mapper;

    @Autowired
    public CategoriesRest(CategorieService service,CategoriesRepositories repositories,ProduitsRepositories produitsRepositories,ModelMapper mapper) {
        this.service = service;
        this.repositories=repositories;
        this.produitsRepositories=produitsRepositories;
        this.mapper=mapper;
    }
    @GetMapping
    public List<Categories> getall(){
        return repositories.findAll();
    }
    @GetMapping("/{id}")
    public Categories findById(@PathVariable("id") long id){
        return repositories.findById(id).orElseThrow(()->new NoSuchElementException());
    }
    @PostMapping
    public Categories create(@RequestBody DtoCategorie categories){
        var categorie = mapper.map(categories,Categories.class);
        categorie.setDate_creation(LocalDate.now());
        return repositories.save(categorie);

    }
    @PutMapping("/{id}")
    public Categories updateCategories(@PathVariable("id") long id , @RequestBody DtoCategorie categories ){
        return service.updateCategories(id,mapper.map(categories,Categories.class));
    }
   @DeleteMapping("/{id}")
    public String deleteCat(@PathVariable("id") long id){
        List<Produits> produitsList = produitsRepositories.findAll();
        for(Produits p : produitsList)
            produitsRepositories.deleteById(p.getId());
        try{
            repositories.deleteById(id);
        }catch (Exception e){
            return "Categorie Not found";
        }
        return "Categorie deleted";
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
