package com.krook1024.game.state;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.*;

@Data
public class SliderState {
    @Setter(AccessLevel.NONE)
    private List<Tile> tiles;

    public SliderState() {
        initialize();
    }

    private void initialize() {
        tiles = List.of(
                new Tile(TileType.TYPE1, new Point(0, 0)),
                new Tile(TileType.TYPE2, new Point(2, 0)),
                new Tile(TileType.TYPE3, new Point(0, 2)),
                new Tile(TileType.TYPE4, new Point(2, 2)),
                new Tile(TileType.TYPE5, new Point(4, 2))
        );
    }

    /**
     * Returns the index in the {@code tiles} list of the tile at the point searched for.
     *
     * @param p the point searched for
     * @return the index of the tile in the {@code tiles} list at the point searched for
     */
    public int findTileIndexAtPoint(Point p) {
        return findTileIndexAtPoint(p.getX(), p.getY());
    }

    /**
     * Returns the index in the {@code tiles} list of the tile at the point searched for.
     *
     * @param x the x co-ordinate of the point searched for
     * @param y the y co-ordinate of the point searched for
     * @return the index of the tile in the {@code tiles} list at the point searched for
     */
    public int findTileIndexAtPoint(int x, int y) {
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);

            if (tile.getTopLeft().getX() == x && tile.getTopLeft().getY() == y)
                return i;

            if (tile.getTopRight().getX() == x && tile.getTopRight().getY() == y)
                return i;

            if (tile.getBotLeft().getX() == x && tile.getBotLeft().getY() == y)
                return i;

            if (tile.getBotRight().getX() == x && tile.getBotRight().getY() == y)
                return i;
        }
        return -1;
    }

    public void stepTileWithIndexAcrossX(int index, Direction direction) {
        Tile t = tiles.get(index);
        if (tiles.get(index) == null) {
            throw new IllegalArgumentException();
        }

        switch (direction) {
            case LEFT:
                t.stepAcrossX(Direction.LEFT);
                break;
            case RIGHT:
                t.stepAcrossX(Direction.RIGHT);
                break;
        }
    }

    public void stepTileWithIndexAcrossY(int index, Direction direction) {
        Tile t = tiles.get(index);
        if (tiles.get(index) == null) {
            throw new IllegalArgumentException();
        }

        switch (direction) {
            case UP:
                t.stepAcrossY(Direction.UP);
                break;
            case DOWN:
                t.stepAcrossY(Direction.DOWN);
                break;
        }
    }
}
