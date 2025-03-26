package com.example.kafka.repo;

import com.example.kafka.model.StatisticDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepo extends JpaRepository<StatisticDTO, Integer> {
    List<StatisticDTO> findAllByStatus(boolean status);

    int countByStatus(boolean b);
}
