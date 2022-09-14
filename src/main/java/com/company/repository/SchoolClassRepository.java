package com.company.repository;

import com.company.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClass,Class> {

    boolean existsByClassName (String className);


}
