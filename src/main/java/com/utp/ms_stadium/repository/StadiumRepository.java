package com.utp.ms_stadium.repository;

import com.utp.ms_stadium.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    List<Stadium> findByCityIgnoreCase(String city);
    List<Stadium> findByOwnerEmail(String ownerEmail);
}