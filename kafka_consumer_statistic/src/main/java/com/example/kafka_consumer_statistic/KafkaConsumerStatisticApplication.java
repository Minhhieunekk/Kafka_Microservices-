package com.example.kafka_consumer_statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@SpringBootApplication
public class KafkaConsumerStatisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerStatisticApplication.class, args);
    }

    @Bean
    JsonMessageConverter jsonMessageConverter() {  //kết hợp với ByteArrayDeserializer để chuyển đổi dữ liệu
        return new JsonMessageConverter();
    }

    @Bean
    DefaultErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
        return new DefaultErrorHandler(new DeadLetterPublishingRecoverer(template),new FixedBackOff(1000L, 2));
        // DeadLetterPublishingRecoverer truyền event lỗi tới DLT topic (dead letter topic - nơi lưu trữ các topic mà consumer đọc lỗi)
        // FixedBackOff là method xử lý lại topic lỗi (tham số đầu tiên là thời gian chạy lại, tham số thứ 2 là số lần chạy) - nếu vẫn lỗi thì mới truyền về DLT topic
    }

}
