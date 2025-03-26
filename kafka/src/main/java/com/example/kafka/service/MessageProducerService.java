package com.example.kafka.service;

import com.example.kafka.model.MessageDTO;
import com.example.kafka.model.StatisticDTO;
import com.example.kafka.repo.MessageRepo;
import com.example.kafka.repo.StatisticRepo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class MessageProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private StatisticRepo statisticRepo;

    private final TaskScheduler taskScheduler;

    private ScheduledFuture<?> scheduledTask;

    public MessageProducerService() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(1);
        threadPoolTaskScheduler.initialize();
        this.taskScheduler = threadPoolTaskScheduler;
    }

    public void startScheduler() {
        scheduledTask = taskScheduler.scheduleAtFixedRate(this::producer, 1000);
    }

    public void stopScheduler() {
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
            scheduledTask = null;
        }
    }
    @PostConstruct
    public void init() { //start schedule
        startScheduler();
    }

    public void producer() {
        List<MessageDTO> messages = messageRepo.findAllByStatus(false);
        for (MessageDTO messageDTO : messages) {
            kafkaTemplate.send("notification", messageDTO).whenComplete((result, ex) -> {
                if (ex == null) {
                    messageDTO.setStatus(true);
                    messageRepo.save(messageDTO);
                }
            });
        }

        List<StatisticDTO> statistics = statisticRepo.findAllByStatus(false);
        for (StatisticDTO statisticDTO : statistics) {
            kafkaTemplate.send("statistic", statisticDTO);
            statisticDTO.setStatus(true);
            statisticRepo.save(statisticDTO);
        }


        if (messageRepo.countByStatus(false) == 0 && statisticRepo.countByStatus(false) == 0) {
            stopScheduler();
        }
    }
}

