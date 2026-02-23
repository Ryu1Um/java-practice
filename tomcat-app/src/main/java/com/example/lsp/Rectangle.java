package com.example.lsp;

import lombok.Data;

@Data
public class Rectangle {

    int width;

    int height;

    public int getArea() {
        return this.width * this.height;
    }
}
