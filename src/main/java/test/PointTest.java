package test;

import com.krook1024.game.state.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void Point() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 1);
        assertEquals(0, a.getX());
        assertEquals(0, a.getY());
        assertEquals(0, b.getX());
        assertEquals(1, b.getY());
    }

    @Test
    void distanceTo() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 1);
        Point c = new Point(1, 0);
        Point d = new Point(1, 1);

        assertEquals(0, a.distanceTo(a));
        assertEquals(1, a.distanceTo(b));
        assertEquals(1, a.distanceTo(1, 0));
        assertEquals(1, a.distanceTo(c));
        assertEquals(1.4142, a.distanceTo(d), 0.001);
    }
}