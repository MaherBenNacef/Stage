package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.sql.Timestamp;


import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    private String nom;
    private int qte;
    private Timestamp date_creation;
    private Timestamp date_modif;
    @OneToMany(mappedBy = "categories", cascade = CascadeType.REMOVE)
    @JsonIgnore
    public Set<Produits> produits;

    public Categories(String nom, int qte, Timestamp date_creation, Timestamp date_modif) {
        this.nom = nom;
        this.qte = qte;
        this.date_creation = date_creation;
        this.date_modif = date_modif;
    }




    
    
}
