package com.example.kafka.controller;

import com.example.kafka.model.AccountDTO;
import com.example.kafka.model.MessageDTO;
import com.example.kafka.model.StatisticDTO;
import com.example.kafka.repo.MessageRepo;
import com.example.kafka.repo.StatisticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    StatisticRepo statisticRepo;

    @PostMapping("/new")
    public AccountDTO create(@RequestBody AccountDTO accountDTO) {

        StatisticDTO statisticDTO = new StatisticDTO("Account " + accountDTO.getEmail()+" is created",new Date());

        MessageDTO messageDTO=new MessageDTO();
        messageDTO.setTo((accountDTO.getEmail()));
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Ok subject");
        messageDTO.setContent("Ok content");
        messageDTO.setStatus(false);
        statisticDTO.setStatus(false);

        messageRepo.save(messageDTO);
        statisticRepo.save(statisticDTO);


        return accountDTO;
    }


}
