package com.moon.project_two.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false, unique = true, updatable = false)
    private String departmentName;

    @OneToOne
    @JoinColumn
    private Doctor headDoctor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "my_dpt_doctors", 
        joinColumns = @JoinColumn(name = "dpt_id"), 
        inverseJoinColumns = @JoinColumn(name = "doctor_id")   
    )
    private List<Doctor> doctors = new ArrayList<>(); 

}
