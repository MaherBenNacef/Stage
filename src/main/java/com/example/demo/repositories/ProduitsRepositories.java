package com.example.demo.repositories;

import com.example.demo.models.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProduitsRepositories extends JpaRepository<Produits,Long> {

     //List<Produits>findByCategories_Id(long id);
     //List<Produits> findByCategories_Id(long idCAt);

}
