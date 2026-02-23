package com.example.lsp;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    @Test
    void test() {
        var rectangle = ServiceLoader.load(AbstractRectangleFactory.class)
                .findFirst().orElseThrow().get();

        rectangle.setHeight(10);
        rectangle.setWidth(3);

        if (rectangle instanceof Square square) {
            assertEquals(9, square.getArea());
        } else {
            assertEquals(30, rectangle.getArea());
        }

    }
}
