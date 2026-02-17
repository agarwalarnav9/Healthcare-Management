package com.moon.project_two.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(   uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_name_email",
            columnNames = { "name", "email" }
        )
    }, 
    indexes = {
            @Index(
                name = "idx_name_specialization",
                columnList = "name, specialization"
            )
        }
    )
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String name; 

    @Column(nullable = false, unique = true, length = 100)
    private String email; 

    @Column(nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "doctor",cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>(); 

    @ManyToMany(mappedBy = "doctors", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Department> departments = new ArrayList<>(); 

}
