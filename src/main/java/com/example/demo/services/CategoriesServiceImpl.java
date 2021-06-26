package com.example.demo.services;
import com.example.demo.models.Categories;
import com.example.demo.models.Produits;
import com.example.demo.repositories.CategoriesRepositories;
import com.example.demo.repositories.ProduitsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;

@Service
public class CategoriesServiceImpl implements CategorieService {

    private CategoriesRepositories repositories;
    private ProduitsRepositories produitsRepositories;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepositories repositories,ProduitsRepositories produitsRepositories) {
        this.repositories = repositories;
        this.produitsRepositories=produitsRepositories;
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
       var c = findCategoriesById(id);
        repositories.deleteById(id);
        return c;
    }

    @Override
    public Categories updateCategories(long id, Categories categories) {
        var categoriesDb = findCategoriesById(id);
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
        Optional<Categories> categories = repositories.findById(id);
        return  categories.orElseThrow(()->new NoSuchElementException());
    }

    public void deleteproduit(Long produitId)  {
        produitsRepositories.deleteById(produitId);
    }




    @Override
    public String categorydeleteEntity(long id) {

        List<Produits> produitsList = produitsRepositories.findAll();
        for(Produits p : produitsList)
            produitsRepositories.deleteById(p.getId());
        repositories.deleteById(id);
        return "Categorie deleted";
    }

    public Set<Produits> setListProduits(Set<Produits> listDb, Set<Produits> listBody){
        for (Produits p: listBody) {
            if (!listDb.contains(p))
                listDb.add(p);
        }
        return listDb;
    }



}
