package com.moon.project_two.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String name; 

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate; 

    @Column(nullable = false)
    private String gender;

    @CreationTimestamp
    @Column(updatable  = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToMany(
        mappedBy = "patient",
        cascade = {CascadeType.REMOVE}, 
        orphanRemoval = true, 
        fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>(); 

    @OneToOne(
            mappedBy = "patient",
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST,CascadeType.MERGE}, 
            orphanRemoval = true,
            fetch = FetchType.LAZY
        )
    @ToString.Exclude
    private Insurance insurance;

}
