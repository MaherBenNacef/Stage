package com.example.demo.EndPoint;

import com.example.demo.Services.CategorieService;
import com.example.demo.models.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categories")
public class CategoriesRest {

    private CategorieService service;

    @Autowired
    public CategoriesRest(CategorieService service) {
        this.service = service;
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
    public Categories delete(@PathVariable("id") long id){
        return service.deleteCategories(id);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
