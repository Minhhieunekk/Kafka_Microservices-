package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    NewTopic notification() {
        // topic name, partition numbers, replication number
        return new NewTopic("notification", 3, (short) 3);
    }
    @Bean
    NewTopic statistic(){
        return new NewTopic("statistic", 3, (short) 3);
    }
}
