package com.example.pattern.decorator;

import com.example.dto.TaskData;
import com.example.pattern.adapter.FindTaskByIdSpi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpringCachingFindTaskByIdDecoratorTest {

    @Mock
    FindTaskByIdSpi delegate;

    @Mock
    Cache cache;

    @InjectMocks
    SpringCachingFindTaskByIdDecorator decorator;

    @Test
    void findTaskById_TaskIsCached_ReturnsNotEmptyOptional() {
        // given
        var taskId = UUID.randomUUID();
        var task = new TaskData(taskId);

        doReturn(task).when(this.cache).get(taskId, TaskData.class);

        // when
        var optional = this.decorator.findTaskById(taskId);

        // then
        assertEquals(Optional.of(task), optional);
        verify(this.cache, never()).put(any(), any());
    }

    @Test
    void findTaskById_TaskIsNotCachedButExist_ReturnsNotEmptyOptional() {
        // given
        var taskId = UUID.randomUUID();
        var task = new TaskData(taskId);

        // when
        doReturn(Optional.of(task)).when(this.delegate).findTaskById(taskId);

        var optional = this.decorator.findTaskById(taskId);

        // then
        assertEquals(Optional.of(task), optional);

        verify(this.cache).put(taskId, task);

    }

    @Test
    void findTaskById_TaskDoesNotExist_ReturnsEmptyOptional() {
        // given
        var taskId = UUID.randomUUID();

        // when
        var optional = this.decorator.findTaskById(taskId);

        // then
        assertEquals(Optional.empty(), optional);

        verify(this.cache, never()).put(any(), any());

    }
}
