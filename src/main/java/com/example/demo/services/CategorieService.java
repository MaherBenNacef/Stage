package com.example.demo.services;

import com.example.demo.models.Categories;


import java.util.List;

public interface CategorieService {

    List<Categories> getAllCategories();
    Categories createCategories(Categories categories);
    Categories deleteCategories(long id);
    Categories updateCategories(long id,Categories categories);
    Categories findCategoriesById(long id);
    public String categorydeleteEntity(long id);
}
