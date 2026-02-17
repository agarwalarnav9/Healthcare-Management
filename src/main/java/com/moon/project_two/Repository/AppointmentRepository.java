package com.moon.project_two.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moon.project_two.Entity.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
