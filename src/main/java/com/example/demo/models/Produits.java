package com.example.demo.models;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data

public class Produits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private int qte;
    private Boolean disponibilite;
    private LocalDate date_creation;
    private LocalDate date_modif;
    @ManyToOne
    private Categories categories;

    public Produits(String nom, int qte, Boolean disponibilite, LocalDate date_creation, LocalDate date_modif) {
        this.nom = nom;
        this.qte = qte;
        this.disponibilite = disponibilite;
        this.date_creation = date_creation;
        this.date_modif = date_modif;
    }

    public Produits() {
    }


    
}
