package com.krook1024.game.state;

public enum TileType {
    EMPTY, // unused
    TYPE1,
    TYPE2,
    TYPE3,
    TYPE4,
    TYPE5;

    /**
     * Returns the instance represented by the value specified.
     *
     * @param value the value representing an instance
     * @return the instance represented by the value specified.
     */
    public TileType of(int value) {
        if (value < 0 || value >= values().length) {
            throw new IllegalArgumentException();
        }
        return values()[value];
    }

    /**
     * Returns the integer value that represents this instance.
     *
     * @return the integer value that represents this instance
     */
    public int getValue() {
        return ordinal();
    }
}
