package com.krook1024.game.state;

import com.krook1024.game.state.*;
import javafx.scene.control.Slider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SliderStateTest {

    @Test
    void findTileIndexAtPoint() {
        SliderState state = new SliderState(SliderState.INITIAL);
        int i = state.findTileIndexAtPoint(0, 0);
        assertEquals(0, i);

        i = state.findTileIndexAtPoint(new Point(4, 2));
        assertEquals(4, i);
    }

    @Test
    void findTileIndexByTopLeftAtPoint() {
        SliderState state = new SliderState(SliderState.INITIAL);
        int i = state.findTileIndexByTopLeftAtPoint(0, 0);
        assertEquals(0, i);

        i = state.findTileIndexByTopLeftAtPoint(2, 0);
        assertEquals(1, i);

        i = state.findTileIndexByTopLeftAtPoint(0, 2);
        assertEquals(2, i);

        i = state.findTileIndexByTopLeftAtPoint(2, 2);
        assertEquals(3, i);

        i = state.findTileIndexByTopLeftAtPoint(4, 2);
        assertEquals(4, i);
    }

    @Test
    void stepTileWithIndex() {
        SliderState state = new SliderState(SliderState.INITIAL);
        state.stepTileWithIndex(4, Direction.UP, Axis.Y);

        Point topLeft = new Point(4, 1),
                topRight = new Point(5, 1),
                botLeft = new Point(4, 2),
                botRight = new Point(5, 2);

        Assertions.assertEquals(new Tile(TileType.TYPE5, topLeft, topRight, botLeft, botRight), state.getTiles().get(4));

        assertThrows(IllegalArgumentException.class, () -> state.stepTileWithIndex(30, Direction.UP, Axis.Y));
    }

    @Test
    void isEmptySpace() {
        SliderState state = new SliderState(SliderState.INITIAL);
        assertTrue(state.isEmptySpace(4, 0));
        assertFalse(state.isEmptySpace(new Point(0, 0)));
    }

    @Test
    void isEmptySpaceNextToPoint() {
        SliderState state = new SliderState(SliderState.INITIAL);
        assertFalse(state.isEmptySpaceNextToPoint(4, 0, Direction.LEFT, Axis.X));
        assertTrue(state.isEmptySpaceNextToPoint(4, 0, Direction.RIGHT, Axis.X));
    }

    @Test
    void isSolved() {
        SliderState state = new SliderState(SliderState.INITIAL);
        assertFalse(state.isSolved());

        List<Tile> tiles = List.of(
                new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)),
                new Tile(TileType.TYPE2, new Point(2, 0), new Point(3, 0), new Point(3, 1), new Point(3, 1)),
                new Tile(TileType.TYPE3, new Point(0, 2), new Point(0, 2), new Point(0, 3), new Point(1, 3)),
                new Tile(TileType.TYPE4, new Point(3, 2), new Point(3, 2), new Point(2, 3), new Point(3, 3)),
                new Tile(TileType.TYPE5, new Point(1, 1), new Point(2, 1), new Point(1, 2), new Point(2, 2))
        );
        SliderState solvedState = new SliderState(tiles);
        assertTrue(solvedState.isSolved());
    }

    @Test
    void testToString() {
        SliderState state = new SliderState(SliderState.INITIAL);

        assertEquals(
                "1 1 2 2     \n" +
                        "1     2     \n" +
                        "3     4 5 5 \n" +
                        "3 3 4 4 5 5 \n",
                state.toString()
        );

        state = new SliderState(SliderState.NEAR_WIN);
        assertEquals(
                "1 1     2 2 \n" +
                        "1 5 5     2 \n" +
                        "3 5 5 4     \n" +
                        "3 3 4 4     \n",
                state.toString());
    }
}