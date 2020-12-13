package ru.points;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsTest {

    @Test
    public void distanceTest() {
        Point p1 = new Point(4.0D, 7.0D);
        Point p2 = new Point(5.0D, 8.0D);
        Assert.assertEquals(p2.distance(p1), 1.4142135623730951D, 0.1D);
        assert p2.distance(p1) == 1.4142135623730951D;

    }
}