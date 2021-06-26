package com.example.demo;


import com.example.demo.services.ProduitsService;
import com.example.demo.models.Categories;
import com.example.demo.models.Produits;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private ProduitsService produitsService;
    @Autowired
    private TestRestTemplate restTemplate;
    private String url="http://localhost:8080/";
    private String c="/categories/";

    @Test
     void createCategorie() {
        LocalDate date = LocalDate.now();
        Categories categories = new Categories("testClass",20,date,date);
        ResponseEntity<Categories> postResponse = restTemplate.postForEntity(url + c, categories, Categories.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    void updateCategorie() {
        int id = 31;
        Categories categories = restTemplate.getForObject(url + c + id, Categories.class);
        categories.setNom("LG");
        categories.setQte(9);
        restTemplate.put(url + c + id, categories);
        Categories updatedCategorie = restTemplate.getForObject(url + c + id, Categories.class);
        assertNotNull(updatedCategorie);
    }

    @Test
     void getAllCategorie() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url + "/categories",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
     void deleteCategorie() {
        int id = 20;
        Categories categories = restTemplate.getForObject(url + c + id, Categories.class);
        assertNotNull(categories);
        restTemplate.delete(url + c+ id);
    }

    @Test
     void testGetcategorieseById() {

        Categories categories = restTemplate.getForObject(url + "/categories/14", Categories.class);
        assertNotNull(categories);
    }

    @Test
     void createProduits(){
        LocalDate date = LocalDate.now();
        Produits produits = new Produits("testProduit",44,true,date,date);
        var p =produitsService.createProduits(produits,10);
        assertNotNull(p);
    }
}
