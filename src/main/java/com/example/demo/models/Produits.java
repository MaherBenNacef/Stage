package com.example.demo.models;
import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;



@Entity
@Data

public class Produits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private int qte;
    private Boolean disponibilite;
    private Timestamp date_creation;
    private Timestamp date_modif;
    @ManyToOne
    private Categories categories;

    public Produits(String nom, int qte, Boolean disponibilite, Timestamp date_creation, Timestamp date_modif) {
        this.nom = nom;
        this.qte = qte;
        this.disponibilite = disponibilite;
        this.date_creation = date_creation;
        this.date_modif = date_modif;
    }

    public Produits() {
    }


    
}
