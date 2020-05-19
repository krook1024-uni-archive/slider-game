package test;

import com.krook1024.game.state.Axis;
import com.krook1024.game.state.Point;
import com.krook1024.game.state.Tile;
import com.krook1024.game.state.TileType;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.krook1024.game.state.Axis.X;
import static com.krook1024.game.state.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    @Test
    void Tile() {
        assertThrows(IllegalArgumentException.class, () -> new Tile(TileType.TYPE1, new Point(0, 0), new Point(3, 0), new Point(0, 1), new Point(0, 1)));

        Tile tile = new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1));
        assertEquals(new Point(0, 0), tile.getTopLeft());
        assertEquals(new Point(1, 0), tile.getTopRight());
        assertEquals(new Point(0, 1), tile.getBotLeft());
        assertEquals(new Point(0, 1), tile.getBotRight());
    }

    @Test
    void isValidTile() {
        assertFalse(Tile.isValidTile(TileType.TYPE1, new Point(0, 0), new Point(3, 0), new Point(0, 1), new Point(0, 1)));
        assertTrue(Tile.isValidTile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)));
        assertTrue(Tile.isValidTile(TileType.TYPE5, new Point(4, 2), new Point(5, 2), new Point(4, 3), new Point(5, 3)));
    }

    @Test
    void stepAcrossX() {
        Tile tile = new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1));
        tile.step(RIGHT, Axis.X);
        assertEquals(new Tile(TileType.TYPE1, new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(1, 1)), tile);
        tile.step(LEFT, Axis.X);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)), tile);
        tile.step(LEFT, Axis.X);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)), tile);
        IntStream.range(0, 100).forEach(i -> tile.step(RIGHT, X));
        assertEquals(new Tile(TileType.TYPE1, new Point(4, 0), new Point(5, 0), new Point(4, 1), new Point(4, 1)), tile);
    }

    @Test
    void stepAcrossY() {
        Tile tile = new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1));
        tile.step(DOWN, Axis.Y);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 1), new Point(1, 1), new Point(0, 2), new Point(0, 2)), tile);
        tile.step(UP, Axis.Y);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)), tile);
        tile.step(UP, Axis.Y);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)), tile);
        IntStream.range(0, 100).forEach(i -> tile.step(RIGHT, X));
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 2), new Point(1, 2), new Point(0, 3), new Point(0, 3)), tile);
    }
}