package com.krook1024.game.state;

import javafx.scene.control.Slider;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents the current state of the game.
 */
@Data
public class SliderState {
    /**
     * The tiles in the current game.
     */
    @Setter(AccessLevel.NONE)
    private List<Tile> tiles = List.of(
            new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)),
            new Tile(TileType.TYPE2, new Point(4, 0), new Point(5, 0), new Point(5, 1), new Point(5, 1)),
            new Tile(TileType.TYPE3, new Point(0, 2), new Point(0, 2), new Point(0, 3), new Point(1, 3)),
            new Tile(TileType.TYPE4, new Point(3, 2), new Point(3, 2), new Point(2, 3), new Point(3, 3)),
            new Tile(TileType.TYPE5, new Point(1, 1), new Point(2, 1), new Point(1, 2), new Point(2, 2))
    );

    /**
     * The initial state of the tiles.
     */
    public static final List<Tile> INITIAL = List.of(
            new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)),
            new Tile(TileType.TYPE2, new Point(2, 0), new Point(3, 0), new Point(3, 1), new Point(3, 1)),
            new Tile(TileType.TYPE3, new Point(0, 2), new Point(0, 2), new Point(0, 3), new Point(1, 3)),
            new Tile(TileType.TYPE4, new Point(3, 2), new Point(3, 2), new Point(2, 3), new Point(3, 3)),
            new Tile(TileType.TYPE5, new Point(4, 2), new Point(5, 2), new Point(4, 3), new Point(5, 3))
    );

    /**
     * A near-win state.
     */
    public static final List<Tile> NEAR_WIN = List.of(
            new Tile(TileType.TYPE1, new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)),
            new Tile(TileType.TYPE2, new Point(4, 0), new Point(5, 0), new Point(5, 1), new Point(5, 1)),
            new Tile(TileType.TYPE3, new Point(0, 2), new Point(0, 2), new Point(0, 3), new Point(1, 3)),
            new Tile(TileType.TYPE4, new Point(3, 2), new Point(3, 2), new Point(2, 3), new Point(3, 3)),
            new Tile(TileType.TYPE5, new Point(1, 1), new Point(2, 1), new Point(1, 2), new Point(2, 2))
    );

    /**
     * The constructor that accepts no arguments.
     */
    public SliderState() { }

    /**
     * The constructor that will initialize the current list of tiles with the given list of tiles.
     * @param tiles the list of {@link Tile}s
     */
    public SliderState(List<Tile> tiles) {
        this.tiles = tiles;
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

    /**
     * Returns the index in the {@code tiles} list of the tile whose top-left is at the point searched for.
     * This is necessary because in a {@code GridPane} you can only find out the top-left co-ordinates
     * when the user clicks on an element.
     *
     * @param x the x co-ordinate of the point searched for
     * @param y the y co-ordinate of the point searched for
     * @return the index of the tile in the {@code tiles} list at the point searched for
     */
    public int findTileIndexByTopLeftAtPoint(int x, int y) {
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);

            if (tile.getTopLeft().getX() == x && tile.getTopLeft().getY() == y)
                return i;

            if (tile.getTopRight().getX() == x + 1 && tile.getTopRight().getY() == y)
                return i;

            if (tile.getBotLeft().getX() == x && tile.getBotLeft().getY() == y + 1)
                return i;

            if (tile.getBotRight().getX() == x + 1 && tile.getBotRight().getY() == y + 1)
                return i;

        }
        return -1;
    }

    /**
     * Steps a tile with the given index across the specified axis in the specified direction (LEFT | RIGHT).
     *
     * @param index the index of the tile in the tiles list
     * @param direction the direction
     * @param axis the axis
     */
    public void stepTileWithIndex(int index, Direction direction, Axis axis) {
        if (index < 0 || index > tiles.size()) {
            throw new IllegalArgumentException();
        }
        Tile t = tiles.get(index);

        if (axis == Axis.X) {
            if (!(isEmptySpace(t.getTopLeft().getX() + direction.getValue(), t.getTopLeft().getY(), index) &&
                    isEmptySpace(t.getTopRight().getX() + direction.getValue(), t.getTopRight().getY(), index) &&
                    isEmptySpace(t.getBotLeft().getX() + direction.getValue(), t.getBotLeft().getY(), index) &&
                    isEmptySpace(t.getBotRight().getX() + direction.getValue(), t.getBotRight().getY(), index)))
                return;
        } else if (axis == Axis.Y) {
            if (!(isEmptySpace(t.getTopLeft().getX(), t.getTopLeft().getY() + direction.getValue(), index) &&
                    isEmptySpace(t.getTopRight().getX(), t.getTopRight().getY() + direction.getValue(), index) &&
                    isEmptySpace(t.getBotLeft().getX(), t.getBotLeft().getY() + direction.getValue(), index) &&
                    isEmptySpace(t.getBotRight().getX(), t.getBotRight().getY() + direction.getValue(), index)))
                return;
        }

        t.step(direction, axis);
    }

    /**
     * Tells whether a point is empty.
     *
     * @param p the point
     * @return whether the point is empty
     */
    public boolean isEmptySpace(Point p) {
        return isEmptySpace(p.getX(), p.getY(), -1);
    }

    /**
     * Tells whether a point is empty.
     *
     * @param x the x co-ordinate of the point
     * @param y the y co-ordinate of the point
     * @return whether the point is empty
     */
    public boolean isEmptySpace(int x, int y) {
        return isEmptySpace(x, y, -1);
    }

    /**
     * Tells whether a point is empty.
     *
     * @param x the x co-ordinate of the point
     * @param y the y co-ordinate of the point
     * @param exclude the tile id to be excluded from the search
     * @return whether the point is empty
     */
    public boolean isEmptySpace(int x, int y, int exclude) {
        List<Tile> tilesAtPoint = tiles
                .stream()
                .filter(tile -> findTileIndexAtPoint(x, y) != -1 && findTileIndexAtPoint(x, y) != exclude)
                .collect(Collectors.toList());
        return tilesAtPoint.size() == 0;
    }

    /**
     * Tells whether a point next to the specified point across the
     * specified axis in the specified direction is empty or not.
     * @param p the point
     * @param direction the direction
     * @param axis the axis
     * @return whether a point next to the specified point across the specified axis in the specified direction is empty
     */
    public boolean isEmptySpaceNextToPoint(Point p, Direction direction, Axis axis) {
        return isEmptySpaceNextToPoint(p.getX(), p.getY(), direction, axis);
    }

    /**
     * Tells whether a point next to the specified point across the
     * specified axis in the specified direction is empty or not.
     * @param x co-ordinate the x of the point
     * @param y co-ordinate the y of the point
     * @param direction the direction
     * @param axis the axis
     * @return whether a point next to the specified point across the specified axis in the specified direction is empty
     */
    public boolean isEmptySpaceNextToPoint(int x, int y, Direction direction, Axis axis) {
        switch (axis) {
            case X:
                return isEmptySpace(x + direction.getValue(), y);
            case Y:
                return isEmptySpace(x, y + direction.getValue());
        }
        return true;
    }

    /**
     * Tells whether the game is solved.
     * @return whether the game is solved
     */
    public boolean isSolved() {
        Tile fullTile = tiles.get(tiles.size() - 1);

        // The game is solved if there is no empty space around the full tile.

        if (isEmptySpaceNextToPoint(fullTile.getTopLeft(), Direction.UP, Axis.Y) ||
            isEmptySpaceNextToPoint(fullTile.getTopLeft(), Direction.LEFT, Axis.X))
            return false;

        if (isEmptySpaceNextToPoint(fullTile.getTopRight(), Direction.UP, Axis.Y) ||
            isEmptySpaceNextToPoint(fullTile.getTopRight(), Direction.RIGHT, Axis.X))
            return false;

        if (isEmptySpaceNextToPoint(fullTile.getBotLeft(), Direction.DOWN, Axis.Y) ||
            isEmptySpaceNextToPoint(fullTile.getBotLeft(), Direction.LEFT, Axis.X))
            return false;

        if (isEmptySpaceNextToPoint(fullTile.getBotRight(), Direction.DOWN, Axis.Y) ||
            isEmptySpaceNextToPoint(fullTile.getBotRight(), Direction.RIGHT, Axis.X))
            return false;

        return true;
    }

    /**
     * Converts the current state to a string.
     * @return a string represting the current state.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                int tileIndexAtPoint = findTileIndexAtPoint(j, i);
                if (tileIndexAtPoint != -1) {
                    Tile t = tiles.get(tileIndexAtPoint);
                    sb.append(t.getType().getValue()).append(' ');
                } else {
                    sb.append("  ");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * A main class in SliderState that only servers testing purposes.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SliderState state = new SliderState();
        System.out.println(state);

        state = new SliderState();
        System.out.println(state);
    }
}
