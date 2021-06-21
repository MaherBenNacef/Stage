package com.example.demo.Services;

import com.example.demo.models.Produits;

import java.util.List;

public interface ProduitsService {
    List<Produits> getAllProduits();
    Produits createProduits(Produits produits, long idCategorie);
    Produits deleteProduits(long id);
    Produits updateProduits(long id,Produits produits);
    Produits findProduitsById(long id);
}
