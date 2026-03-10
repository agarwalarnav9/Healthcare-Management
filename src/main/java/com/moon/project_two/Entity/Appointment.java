package com.moon.project_two.Entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(nullable = false)
    private String reason; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Patient patient; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Doctor doctor;

}
