package com.example.pattern;

import com.example.dto.TaskData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class FindTaskByIdSpiMappingSqlQueryTest {

    @Spy
    FindTaskByIdSpiMappingSqlQuery adapter;

    @Test
    void findTaskById_CallsFindObjectByNamedParam_ReturnsOptional() {
        // given
        var id = UUID.randomUUID();
        var task = new TaskData(id);

        doReturn(task).when(this.adapter).findObjectByNamedParam(Map.of("id", id));

        // when
        var optional = this.adapter.findTaskById(id);

        // then
        assertEquals(Optional.of(task), optional);
    }
}
