package com.demo.example.student_library_management_system.repository;

import com.demo.example.student_library_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    public List<Student> findBySemAndDept( String inSem, String inDept);
}
