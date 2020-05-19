package com.krook1024.game.state;

/**
 * This enum represents a direction.
 */
public enum Direction {
    /**
     * The left direction.
     */
    LEFT(-1),
    /**
     *  The right direction.
     */
    RIGHT(1),
    /**
     * The up direction.
     */
    UP(-1),
    /**
     * The down direction.
     */
    DOWN(1),
    /**
     * The none direction.
     */
    NONE(0);

    private final int value;

    /**
     * @param value the vaue representing the direction
     */
    Direction(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the direction.
     * @return the value of the direction
     */
    public int getValue() {
        return value;
    }
}
