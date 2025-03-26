package com.example.kafka.repo;

import com.example.kafka.model.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<MessageDTO, Integer> {
    List<MessageDTO> findAllByStatus(Boolean status);

    int countByStatus(boolean b);
}
