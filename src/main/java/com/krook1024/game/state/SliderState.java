package com.krook1024.game.state;

import javafx.scene.control.Slider;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
public class SliderState {
    @Setter(AccessLevel.NONE)
    private Set<Tile> tiles = new HashSet<>();

    public SliderState() {
        tiles.add(new Tile(TileType.TYPE1,
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(0, 1)));

        tiles.add(new Tile(TileType.TYPE2,
                new Point(2, 0),
                new Point(3, 0),
                new Point(3, 1),
                new Point(3, 1)));

        tiles.add(new Tile(TileType.TYPE3,
                new Point(0, 2),
                new Point(0, 2),
                new Point(0, 3),
                new Point(1, 3)));

        tiles.add(new Tile(TileType.TYPE4,
                new Point(3, 2),
                new Point(3, 2),
                new Point(2, 3),
                new Point(3, 3)));

        tiles.add(new Tile(TileType.TYPE5,
                new Point(4, 2),
                new Point(5, 2),
                new Point(4, 3),
                new Point(5, 3)));
    }
}
