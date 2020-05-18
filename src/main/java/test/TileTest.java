package test;

import com.krook1024.game.state.Point;
import com.krook1024.game.state.Tile;
import com.krook1024.game.state.TileType;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.krook1024.game.state.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    @Test
    void isValid() {
        assertTrue(true);
    }

    @Test
    void stepAcrossX() {
        Tile tile = new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1));
        tile.stepAcrossX(RIGHT);
        assertEquals(new Tile(TileType.TYPE1, new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(1, 1)), tile);
        tile.stepAcrossX(LEFT);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)), tile);
        tile.stepAcrossX(LEFT);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)), tile);
        IntStream.range(0, 100).mapToObj(i -> RIGHT).forEach(tile::stepAcrossX);
        assertEquals(new Tile(TileType.TYPE1, new Point(4, 0), new Point(5, 0), new Point(4, 1), new Point(4, 1)), tile);
    }

    @Test
    void stepAcrossY() {
        Tile tile = new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1));
        tile.stepAcrossY(DOWN);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 1), new Point(1, 1), new Point(0, 2), new Point(0, 2)), tile);
        tile.stepAcrossY(UP);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)), tile);
        tile.stepAcrossY(UP);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)), tile);
        IntStream.range(0, 100).mapToObj(i -> DOWN).forEach(tile::stepAcrossY);
        assertEquals(new Tile(TileType.TYPE1, new Point(0, 2), new Point(1, 2), new Point(0, 3), new Point(0, 3)), tile);
    }
}