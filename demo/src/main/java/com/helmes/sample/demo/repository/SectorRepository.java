package com.helmes.sample.demo.repository;

import com.helmes.sample.demo.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}
