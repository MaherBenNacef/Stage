package com.example.demo.repositories;

import com.example.demo.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepositories extends JpaRepository<Categories,Long> {
}
