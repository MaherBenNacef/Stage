package com.example.demo.services;

import com.example.demo.models.Categories;
import com.example.demo.models.Produits;
import com.example.demo.repositories.CategoriesRepositories;
import com.example.demo.repositories.ProduitsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProduitsServiceImpl implements ProduitsService{

    private ProduitsRepositories repositories;
    private CategoriesRepositories categoriesRepositories;

    @Autowired
    public ProduitsServiceImpl(ProduitsRepositories repositories,CategoriesRepositories categoriesRepositories) {
        this.repositories = repositories;
        this.categoriesRepositories=categoriesRepositories;
    }

    @Override
    public List<Produits> getAllProduits() {

        return repositories.findAll();
    }

    @Override
    public Produits createProduits(Produits produits,long categorieId) {
        if(produits == null)
            throw new NoSuchElementException();
        produits.setDate_creation(new Timestamp(System.currentTimeMillis()));
        Optional<Categories> categorie =categoriesRepositories.findById(categorieId);
        produits.setCategories(categorie.orElseThrow(()-> new NoSuchElementException()));
        return repositories.save(produits);
    }

    @Override
    public Produits deleteProduits(long id) {
        var p = findProduitsById(id);
        repositories.deleteById(id);
        return p;
    }

    @Override
    public Produits updateProduits(long id, Produits produits) {
        var produitsDb= findProduitsById(id);
        if (produits.getDisponibilite()!=produitsDb.getDisponibilite())
            produitsDb.setDisponibilite(produits.getDisponibilite());
        if (produits.getNom()!=produitsDb.getNom())
            produitsDb.setNom(produits.getNom());
        if (produits.getQte()!=produitsDb.getQte())
            produitsDb.setQte(produits.getQte());
        if (produits.getCategories()!=produitsDb.getCategories())
            produitsDb.setCategories(produits.getCategories());
        produitsDb.setDate_modif(new Timestamp(System.currentTimeMillis()));
        return repositories.save(produitsDb);
    }

    @Override
    public Produits findProduitsById(long id) {
        Optional<Produits> produit=repositories.findById(id);
        return produit.orElseThrow(()->new NoSuchElementException());
    }


}
