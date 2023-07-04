package com.springbootPostGresql.entity;

// Importing required package modules

// Importing required classes
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// Class
public class Task {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String taskName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate taskDateCreated;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate taskDateUntil;

    private Boolean taskDone;

    private Boolean taskImportant;

   @OneToOne
   @JoinColumn(name = "user_id")
   private User1 user_id;



}