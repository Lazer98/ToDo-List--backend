package com.springbootPostGresql.entity;


// Importing required package modules

// Importing required classes
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
// Class
public class User1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="email",unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user_id")
    @Column(name="tasks")
    private List<Task> tasks;

}