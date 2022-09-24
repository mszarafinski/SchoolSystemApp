package com.company.repository;

import com.company.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface SchoolClassRepository extends JpaRepository<SchoolClass,Long> {

    boolean existsByClassName (String className);

    SchoolClass findByClassName (String className);


}
