package com.krook1024.game.state;

import lombok.Data;
import lombok.NonNull;

@Data
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
                       (topRight.distanceTo(topLeft) == 0) ;

            /*
                    X X
                    X X
             */
            case TYPE5:
                return true;
        }
        return false;
    }
}
