package com.krook1024.game.state;

/**
 * This enum represents a tile type.
 */
public enum TileType {
    /**
     * An empty tile.
     */
    EMPTY, // unused
    /**
     * A tile with the following structure.
     * x x
     * x
     */
    TYPE1,
    /**
     * A tile with the following structure.
     * x x
     *   x
     */
    TYPE2,
    /**
     * A tile with the following structure.
     * x
     * x x
     */
    TYPE3,
    /**
     * A tile with the following structure.
     *   x
     * x x
     */
    TYPE4,
    /**
     * A tile with the following structure.
     * x x
     * x x
     */
    TYPE5;

    /**
     * Returns the instance represented by the value specified.
     *
     * @param value the value representing an instance
     * @return the instance represented by the value specified.
     */
    public static TileType of(int value) {
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
