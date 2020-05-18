package com.krook1024.game.state;

public enum Direction {
    LEFT(-1),
    RIGHT(1),
    UP(-1),
    DOWN(1);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
