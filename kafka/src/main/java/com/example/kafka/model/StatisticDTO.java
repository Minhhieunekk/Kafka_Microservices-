package com.example.kafka.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatisticDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private Date createdDate;
    private boolean status;


    public StatisticDTO(String s, Date date) {
        this.message = s;
        this.createdDate = date;
    }
}
