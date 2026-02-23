package com.example.lsp;

public class SquareFactory implements AbstractRectangleFactory {

    @Override
    public Rectangle get() {
        return new Square();
    }
}
