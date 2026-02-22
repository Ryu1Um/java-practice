package com.example.pattern.decorator;

import com.example.dto.TaskData;
import com.example.pattern.adapter.FindTaskByIdSpi;

import java.util.Optional;
import java.util.UUID;

abstract class CachingFindTaskByIdDecorator implements FindTaskByIdSpi {

    private FindTaskByIdSpi delegate;

    public CachingFindTaskByIdDecorator(FindTaskByIdSpi delegate) {
        this.delegate = delegate;
    }

    abstract Optional<TaskData> retrieveFromCache(UUID id);

    abstract void storeInCache(TaskData taskData);

    @Override
    public Optional<TaskData> findTaskById(UUID id) {
        return this.retrieveFromCache(id)
                .or(() -> this.delegate.findTaskById(id)
                        .map(task -> {
                            this.storeInCache(task);
                            return task;
                        }));
    }
}
