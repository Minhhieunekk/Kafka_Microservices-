package com.example.kafka.model;

//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class MessageDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "to_email", nullable = false)
    private String to;
    private String toName;
    private String subject;
    private String content;
    private boolean status;


}
