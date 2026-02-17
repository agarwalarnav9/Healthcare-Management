package com.moon.project_two.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moon.project_two.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    boolean existsByEmail(String email);
    
}
