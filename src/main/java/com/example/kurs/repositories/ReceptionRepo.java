package com.example.kurs.repositories;

import com.example.kurs.models.Reception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceptionRepo  extends JpaRepository<Reception, Long> {
    List<Reception> findByAcceptFalse();
}
