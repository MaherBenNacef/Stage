package com.example.demo.repositories;

import com.example.demo.models.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProduitsRepositories extends JpaRepository<Produits,Long> {



}
