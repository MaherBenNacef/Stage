package com.example.demo.repositories;

import com.example.demo.models.Produits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitsRepositories extends JpaRepository<Produits,Long> {
}
