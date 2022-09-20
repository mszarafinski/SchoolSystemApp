package com.company.repository;

import com.company.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student,Long> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    boolean existsById(Long id);

    @Query("Select s from Student s where s.lastName=?1")
    Student findByLastName(String lastName);



//    @Modifying
//    @Query("UPDATE Student s SET s.classId =?1 WHERE s.id=?2")
//    void changeStudentClass( Long NewClassId, Long studentId);


}
