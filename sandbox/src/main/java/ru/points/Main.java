package ru.points;

public class Main {

    public static double distance(Point p1, Point p2) {

        double d = 0;
        d = (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
        return Math.sqrt(d);
    }

    public static void main(String[] args) {

        Point p1 = new Point(4, 7);
        Point p2 = new Point(5, 8);
        System.out.println("Расстояние равно: " + distance(p1, p2));

        System.out.println("Расстояние по расчету вторым способом равно: " + p2.distance(p1));

    }
}