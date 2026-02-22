package com.example.pattern.adapter;

import com.example.dto.TaskData;

import java.util.Optional;
import java.util.UUID;

public interface FindTaskByIdSpi {

    Optional<TaskData> findTaskById(UUID id);
}
