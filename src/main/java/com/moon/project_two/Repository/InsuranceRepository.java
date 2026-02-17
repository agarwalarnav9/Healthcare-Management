package com.moon.project_two.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moon.project_two.Entity.Insurance;


public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
    
    @Query("SELECT i from Insurance i LEFT JOIN FETCH i.patient") 
    List<Insurance> findAll(); 
}
