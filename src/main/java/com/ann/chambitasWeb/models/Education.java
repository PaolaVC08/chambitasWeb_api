package com.ann.chambitasWeb.models;
//import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEdu;

    @ManyToOne
    @JoinColumn(name = "profesionista_id")
    private Profesionista profesionista;

   // private String school;
   // private String titulo;
   // private LocalDate startDate;
   // private LocalDate endDate;
}

