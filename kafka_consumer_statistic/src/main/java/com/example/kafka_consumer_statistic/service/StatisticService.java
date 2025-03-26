package com.example.kafka_consumer_statistic.service;

import com.example.kafka_consumer_statistic.entity.Statistic;
import com.example.kafka_consumer_statistic.repo.StatisticRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatisticService {

    @Autowired
    private StatisticRepo statisticRepo;

    @KafkaListener(id = "statisticGroup",topics = "statistic")
    public void listen(Statistic statistic) {
        log.info("Received : {}", statistic.getMessage());
        statisticRepo.save(statistic);
    }

    @KafkaListener(id = "dltGroup", topics = "statistic-dlt")
    public void dltListen(Statistic statistic) {
        log.info("Received statisticDLT: {}", statistic.getMessage());
    }
}
