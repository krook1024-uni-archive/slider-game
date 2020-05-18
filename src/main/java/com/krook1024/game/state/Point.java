package com.krook1024.game.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Point {
    @NonNull
    private int x, y;

    /**
     * Returns the distance to another point, {@code p}.
     *
     * @param p the othe rpoint
     * @return the distance to {@code p}
     */
    public double distanceTo(Point p) {
        return distanceTo(p.x, p.y);
    }

    /**
     * Returns the distance to the specified x and y.
     * @param x the x co-ordinate
     * @param y the y co-ordinate
     * @return the distance to x and y
     */
    public double distanceTo(int x, int y) {
        return Math.sqrt(
            (this.x - x) * (this.x - x) +
            (this.y - y) * (this.y - y)
        );
    }
}
