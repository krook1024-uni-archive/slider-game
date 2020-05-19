package com.krook1024.game.state;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SliderStateTest {

    @Test
    void findTileIndexAtPoint() {
        SliderState state = new SliderState();
        int i = state.findTileIndexAtPoint(0, 0);
        assertEquals(0, i);

        i = state.findTileIndexAtPoint(new Point(4, 2));
        assertEquals(4, i);
    }

    @Test
    void findTileIndexByTopLeftAtPoint() {
        SliderState state = new SliderState();
        int i = state.findTileIndexByTopLeftAtPoint(0, 0);
        assertEquals(0, i);

        i = state.findTileIndexByTopLeftAtPoint(2, 2);
        assertEquals(3, i);
    }

    @Test
    void stepTileWithIndex() {
        SliderState state = new SliderState();
        state.stepTileWithIndex(4, Direction.UP, Axis.Y);

        Point topLeft = new Point(4, 1),
                topRight = new Point(5, 1),
                botLeft = new Point(4, 2),
                botRight = new Point(5, 2);

        assertEquals(new Tile(TileType.TYPE5, topLeft, topRight, botLeft, botRight), state.getTiles().get(4));
    }

    @Test
    void isEmptySpace() {
        SliderState state = new SliderState();
        assertTrue(state.isEmptySpace(4, 0));
        assertFalse(state.isEmptySpace(0, 0));
    }

    @Test
    void isEmptySpaceNextToPoint() {
        SliderState state = new SliderState();
        assertFalse(state.isEmptySpaceNextToPoint(4, 0, Direction.LEFT, Axis.X));
        assertTrue(state.isEmptySpaceNextToPoint(4, 0, Direction.RIGHT, Axis.X));
    }

    @Test
    void isSolved() {
        SliderState state = new SliderState();
        assertFalse(state.isSolved());

        List<Tile> tiles = List.of(
                new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)),
                new Tile(TileType.TYPE2, new Point(2, 0), new Point(3, 0), new Point(3, 1), new Point(3, 1)),
                new Tile(TileType.TYPE3, new Point(0, 2), new Point(0, 2), new Point(0, 3), new Point(1, 3)),
                new Tile(TileType.TYPE4, new Point(3, 2), new Point(3, 2), new Point(2, 3), new Point(3, 3)),
                new Tile(TileType.TYPE5, new Point(1, 1), new Point(2, 1), new Point(1, 2), new Point(2, 2))
        );
        SliderState solvedState = new SliderState(tiles);
        System.out.println("solvedState = " + solvedState);
        assertTrue(solvedState.isSolved());
    }
}