package com.example.lsp;

public class RectangleFactory implements AbstractRectangleFactory {

    @Override
    public Rectangle get() {
        return new Rectangle();
    }
}
