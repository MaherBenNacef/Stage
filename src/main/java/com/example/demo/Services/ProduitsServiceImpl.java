package com.example.demo.Services;

import com.example.demo.models.Produits;
import com.example.demo.repositories.CategoriesRepositories;
import com.example.demo.repositories.ProduitsRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

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
        produits.setDate_creation(LocalDate.now());
        produits.setCategories(categoriesRepositories.findById(categorieId).get());
        return repositories.save(produits);
    }

    @Override
    public Produits deleteProduits(long id) {
        Produits p = findProduitsById(id);
        repositories.deleteById(id);
        return p;
    }

    @Override
    public Produits updateProduits(long id, Produits produits) {
        Produits produitsDb= findProduitsById(id);
        if (produits.getDisponibilite()!=produitsDb.getDisponibilite())
            produitsDb.setDisponibilite(produits.getDisponibilite());
        if (produits.getNom()!=produitsDb.getNom())
            produitsDb.setNom(produits.getNom());
        if (produits.getQte()!=produitsDb.getQte())
            produitsDb.setQte(produits.getQte());
        if (produits.getCategories()!=produitsDb.getCategories())
            produitsDb.setCategories(produits.getCategories());
        produitsDb.setDate_modif(LocalDate.now());
        return repositories.save(produitsDb);
    }

    @Override
    public Produits findProduitsById(long id) {

         if (repositories.findById(id).isPresent()){
             return repositories.findById(id).get();
         }else throw new NoSuchElementException("Produit n'existe pas !");
    }
}
