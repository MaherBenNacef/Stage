package com.example.demo.Services;

import com.example.demo.models.Categories;
import com.example.demo.models.Produits;
import com.example.demo.repositories.CategoriesRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CategoriesServiceImpl implements CategorieService {

    private CategoriesRepositories repositories;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepositories repositories) {
        this.repositories = repositories;
    }

    @Override
    public List<Categories> getAllCategories() {
        return repositories.findAll();
    }

    @Override
    public Categories createCategories(Categories categories) {
        categories.setDate_creation(LocalDate.now());
        return repositories.save(categories);
    }

    @Override
    public Categories deleteCategories(long id) {
        Categories c = findCategoriesById(id);
        repositories.deleteById(id);
        return c;
    }

    @Override
    public Categories updateCategories(long id, Categories categories) {
        Categories categoriesDb = findCategoriesById(id);
        if (categories.getNom()!=categoriesDb.getNom())
            categoriesDb.setNom(categories.getNom());
        if (categories.getQte()!=categoriesDb.getQte())
            categoriesDb.setQte(categories.getQte());
        if (categories.getProduits()!=null)
            categoriesDb.setProduits(setListProduits(categoriesDb.getProduits(),categories.getProduits()));
        categoriesDb.setDate_modif(LocalDate.now());
        repositories.save(categoriesDb);
        return categoriesDb;
    }

    @Override
    public Categories findCategoriesById(long id) {
        if (repositories.findById(id).isPresent()){
            return repositories.findById(id).get();
        }else throw new NoSuchElementException("Categories n'existe pas !");
    }
    
    public Set<Produits> setListProduits(Set<Produits> listDb, Set<Produits> listBody){
        for (Produits p: listBody) {
            if (!listDb.contains(p))
                listDb.add(p);
        }
        return listDb;
    }
}
