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

}
