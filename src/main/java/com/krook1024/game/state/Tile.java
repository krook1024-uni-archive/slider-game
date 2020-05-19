package com.krook1024.game.state;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import static com.krook1024.game.state.Direction.*;

@Data
@EqualsAndHashCode
public class Tile {
    @NonNull
    private Point topLeft, topRight, botLeft, botRight;

    @NonNull
    private TileType type = TileType.TYPE1;

    public Tile(TileType tileType, Point topLeft, Point topRight, Point botLeft, Point botRight) {
        if (Tile.isValidTile(tileType, topLeft, topRight, botLeft, botRight)) {
            this.type = tileType;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.botLeft = botLeft;
            this.botRight = botRight;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if a tile is valid.
     *
     * @return true or false whether the tile is valid.
     */
    public boolean isValid() {
        return Tile.isValidTile(this.type, this.topLeft, this.topRight, this.botLeft, this.botRight);
    }

    /**
     * Static function to check whether a tile is valid without needing to have an actual Tile instance.
     *
     * @param topLeft  the top left co-ordinate of the tile
     * @param topRight the top right co-ordinate of the tile
     * @param botLeft  the bottom left co-ordinate of the tile
     * @param botRight the bottom right co-ordinate of the tile
     * @return whether the tile is valid or not.
     */
    public static boolean isValidTile(TileType type, Point topLeft, Point topRight, Point botLeft, Point botRight) {
        if (type != TileType.TYPE5) {
            if (topLeft.distanceTo(botRight) > 2 || topRight.distanceTo(botLeft) > 2) {
                return false;
            }
        }

        switch (type) {
            case EMPTY:
                return false;
            /*
                    X X
                    X
             */
            case TYPE1:
                return (topLeft.getY() == topRight.getY()) &&
                        (topLeft.getX() == botRight.getX()) &&
                        (botLeft.distanceTo(botRight) == 0);

            /*
                    X X
                      X
             */
            case TYPE2:
                return (topLeft.getY() == topRight.getY()) &&
                        (topRight.getX() == botRight.getX()) &&
                        (botLeft.distanceTo(botRight) == 0);

            /*
                    X
                    X X
             */
            case TYPE3:
                return (botLeft.getY() == botRight.getY()) &&
                        (botLeft.getX() == topLeft.getX()) &&
                        (topLeft.distanceTo(topRight) == 0);

            /*
                      X
                    X X
             */
            case TYPE4:
                return (botLeft.getY() == botRight.getY()) &&
                        (botRight.getX() == topRight.getX()) &&
                        (topRight.distanceTo(topLeft) == 0);

            /*
                    X X
                    X X
             */
            case TYPE5:
                return (topLeft.distanceTo(botRight) < 2) &&
                        (botLeft.distanceTo(topRight) < 2);
        }
        return false;
    }

    /**
     * Returns whether the tile is at the edge of the grid.
     *
     * @return whether the tile is at the edge of the grid (-1 if it's at the left edge, 1 if it's at the right edge, 0 if it's not at the edge)
     */
    private int isAtXEdge() {
        if (Math.min(topLeft.getX(), botLeft.getX()) == 0)
            return -1;

        if (Math.max(topRight.getX(), botRight.getX()) == 5)
            return 1;

        return 0;
    }

    /**
     * Returns whether the tile is at the edge of the grid.
     *
     * @return whether the tile is at the edge of the grid (-1 if it's at the left edge, 1 if it's at the right edge, 0 if it's not at the edge)
     */
    private int isAtYEdge() {
        if (Math.min(topLeft.getY(), topRight.getY()) == 0)
            return -1;

        if (Math.max(botLeft.getY(), botRight.getY()) == 3)
            return 1;

        return 0;
    }

    /**
     * Steps a tile across the X axis.
     *
     * @param direction the direction (LEFT | RIGHT)
     */
    public void stepAcrossX(Direction direction) {
        if (isAtXEdge() == 1 && direction == RIGHT || isAtXEdge() == -1 && direction == LEFT) {
            return;
        }

        topLeft.setX(topLeft.getX() + direction.getValue());
        topRight.setX(topRight.getX() + direction.getValue());
        botLeft.setX(botLeft.getX() + direction.getValue());
        botRight.setX(botRight.getX() + direction.getValue());
    }

    /**
     * Steps a tile across the Y axis.
     *
     * @param direction the direction (UP | DOWN)
     */
    public void stepAcrossY(Direction direction) {
        if (isAtYEdge() == 1 && direction == DOWN || isAtYEdge() == -1 && direction == UP) {
            return;
        }

        topLeft.setY(topLeft.getY() + direction.getValue());
        topRight.setY(topRight.getY() + direction.getValue());
        botLeft.setY(botLeft.getY() + direction.getValue());
        botRight.setY(botRight.getY() + direction.getValue());
    }
}
