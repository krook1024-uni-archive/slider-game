package com.krook1024.game.state;

import org.junit.jupiter.api.Test;
import static com.krook1024.game.state.TileType.*;
import static org.junit.jupiter.api.Assertions.*;

class TileTypeTest {

    @Test
    void of() {
        assertEquals(TYPE1, TileType.of(1));
        assertEquals(TYPE2, TileType.of(2));
        assertEquals(TYPE3, TileType.of(3));
        assertEquals(TYPE4, TileType.of(4));
        assertEquals(TYPE5, TileType.of(5));

        assertThrows(IllegalArgumentException.class, () -> TileType.of(666));
    }

    @Test
    void getValue() {
        assertEquals(1, TYPE1.getValue());
        assertEquals(2, TYPE2.getValue());
        assertEquals(3, TYPE3.getValue());
        assertEquals(4, TYPE4.getValue());
        assertEquals(5, TYPE5.getValue());
    }
}