package com.helmes.sample.demo.repository;

import com.helmes.sample.demo.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    Form findTopByOrderByIdDesc();
}
