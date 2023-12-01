package com.example.Enseignant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enseignant {
    @Id
    private long id;
    @NaturalId(mutable = true)
    private Long cin;
    private String nom;
    private String prenom;
    private String mail;
    private String grade;
}
