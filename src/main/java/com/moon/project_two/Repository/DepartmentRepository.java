package com.moon.project_two.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moon.project_two.Entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
}
