package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nom;
    private int qte;
    private LocalDate date_creation;
    private LocalDate date_modif;
    @OneToMany(mappedBy = "categories", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Produits> produits;
}
