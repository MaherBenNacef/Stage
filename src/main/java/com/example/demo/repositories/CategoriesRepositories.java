package com.example.demo.repositories;

import com.example.demo.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepositories extends JpaRepository<Categories,Long> {
}
