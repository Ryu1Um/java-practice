package com.example.pattern.decorator;

import com.example.dto.TaskData;
import com.example.pattern.adapter.FindTaskByIdSpi;
import org.springframework.cache.Cache;

import java.util.Optional;
import java.util.UUID;

public class SpringCachingFindTaskByIdDecorator extends CachingFindTaskByIdDecorator {

    private final Cache cache;

    public SpringCachingFindTaskByIdDecorator(FindTaskByIdSpi delegate, Cache cache) {
        super(delegate);
        this.cache = cache;
    }

    @Override
    Optional<TaskData> retrieveFromCache(UUID id) {
        return Optional.ofNullable(this.cache.get(id, TaskData.class));
    }

    @Override
    void storeInCache(TaskData task) {
        this.cache.put(task.id(), task);
    }
}
