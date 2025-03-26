package com.example.kafka_consumer_statistic.repo;

import com.example.kafka_consumer_statistic.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatisticRepo extends JpaRepository<Statistic, Integer> {
}
